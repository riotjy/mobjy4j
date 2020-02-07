/*******************************************************************************
 * Copyright 2020 riotjy
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package dev.riotjy.mobjy.builder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.model.MjyClass;
import dev.riotjy.mobjy.model.MjyCollectionType;
import dev.riotjy.mobjy.model.MjyModel;
import dev.riotjy.mobjy.model.MjyModelFactory;
import dev.riotjy.mobjy.model.MjyPrimitiveType;
import dev.riotjy.mobjy.model.MjyVersion;
import dev.riotjy.mobjy.util.Recursive;

public class ModelBuilder {

  final static Logger log = LoggerFactory.getLogger(ModelBuilder.class);

  private ModelLoader modelLoader;
  private MjyModel theModel;

  private ModelBuilder() {
  }

  public ModelBuilder(String fileName) {
    this();
    modelLoader = ModelLoader.getLoadedLoader(fileName);
  }

  public MjyModel build() throws Exception {
    buildModel();
    buildClasses();
    buildInheritance();
    if (cyclicInheritanceFound())
      throw new Exception("Cyclic inheritance found.");
    buildImports();
    buildMembers();
    return theModel;
  }

  private boolean buildModel() throws Exception {
    String project = modelLoader.getMapped("project").toString();
    //TODO: conversion from Object to Map
    Map<String, Object> ver = (Map)modelLoader.getMapped("version");
    Map<String, Object> comver = (Map)modelLoader.getMapped("compatver");
    MjyVersion version = MjyModelFactory.makeVersion(
        Integer.parseInt(ver.get("major").toString()), 
        Integer.parseInt(ver.get("minor").toString()), 
        Integer.parseInt(ver.get("revision").toString()), 
        Integer.parseInt(ver.get("candidate").toString()), 
        ver.get("status").toString());
    MjyVersion compatver = MjyModelFactory.makeVersion(
        Integer.parseInt(comver.get("major").toString()), 
        Integer.parseInt(comver.get("minor").toString()), 
        Integer.parseInt(comver.get("revision").toString()), 
        Integer.parseInt(comver.get("candidate").toString()), 
        comver.get("status").toString());
    theModel = MjyModelFactory.makeModel(project, version, compatver);
    return true;
  }
  
  private boolean buildClasses() throws Exception {
    Collection<? extends Object> keys = modelLoader.getKeys();
    for (Object key : keys) {
      if (key.toString().equals("project") ||
          key.toString().equals("version") ||
          key.toString().equals("compatver") ) {
          continue;
      }
      String className = key.toString();
      theModel.addClass(MjyModelFactory.makeClass(className));
    }
    return true;
  }
  
  private boolean buildInheritance() throws Exception {
    Collection<? extends Object> keys = modelLoader.getKeys();
    for (Object key : keys) {
      if (key.toString().equals("project") ||
          key.toString().equals("version") ||
          key.toString().equals("compatver") ) {
          continue;
      }
      String className = key.toString();
      String extendsClass = (String)((Map)modelLoader.getMapped(key.toString())).get("extends");
      if (null == extendsClass) {
        continue;
      }
      MjyClass generalization = theModel.getClassByName(extendsClass);
      theModel.getClassByName(className).setGeneralization(generalization);
    }
    return true;
  }
  
  private boolean buildImports() throws Exception {
    Collection<? extends Object> keys = modelLoader.getKeys();
    for (Object key : keys) {
      if (key.toString().equals("project") ||
          key.toString().equals("version") ||
          key.toString().equals("compatver") ) {
          continue;
      }
      String className = key.toString();
      buildImports(className);
    }
    return true;
  }
  
  private boolean buildImports(String className) throws Exception {
    MjyClass theClass = theModel.getClassByName(className);
    if (null == theClass) {
      return false;
    }

    Map<String, Object> classMap = (Map)modelLoader.getMapped(className);
    Set<String> keys = classMap.keySet();
    for (String key : keys) {
      if (key.equals("java") ||
          key.equals("cpp") ||
          key.equals("golang")) {

        Object mapVal = classMap.get(key);
        if (mapVal instanceof Map<?,?>) {
          Object val = ((Map<?,?>)mapVal).get("class");
          theClass.addLangDepClass(key, val.toString());
          val = ((Map<?,?>)mapVal).get("path");
          theClass.addLangDepResource(key, val.toString());
        } else {
          log.error("External class specification error for class: " + className + " for language: " + key);
        }
        theClass.setExternal(true);
      }
    }
    return true;
  }
  
  private boolean buildMembers() throws Exception {
    Collection<? extends Object> keys = modelLoader.getKeys();
    for (Object key : keys) {
      if (key.toString().equals("project") ||
          key.toString().equals("version") ||
          key.toString().equals("compatver") ) {
          continue;
      }
      String className = key.toString();
      buildMembers(className);
    }
    return true;
  }

  private boolean buildMembers(String className) throws Exception {
    MjyClass theClass = theModel.getClassByName(className);
    if (null == theClass) {
      return false;
    }
    if (theClass.isExternal()) {
      // this class should not have members
      return false;
    }

    Map<String, Object> classMap = (Map)modelLoader.getMapped(className);
    Set<String> members = classMap.keySet();
    for (String member : members) {

      if (member.equals("extends"))
        continue;

      Object type = (classMap).get(member);
      if (type instanceof String) {
        if (type.toString().contains("[]")) {
          theClass.addCollection(
              MjyModelFactory.makeCollection(member,
                  MjyCollectionType.ARRAYLIST,
                  MjyModelFactory.makePrimitive(
                      MjyPrimitiveType.getMjyPrimitiveType(type.toString().replace("[]", "")))));
          theClass.setUsesArrayList(true);
          continue;
        } else {
          theClass.addAttribute(
              MjyModelFactory.makeAttribute(member,
                  MjyModelFactory.makePrimitive(
                      MjyPrimitiveType.getMjyPrimitiveType(type.toString()))));
          continue;
        }
      }
      if (type instanceof Map<?,?>) {
        Map<String,String> mappedType = (Map)type;
        String references = mappedType.get("references");

        MjyClass classTypeUsed = theModel.getClassByName(references);
        if (null == classTypeUsed) {
          // Programmer wants to use an external class, let it do
          //TODO: some classes need importing from another package
          classTypeUsed = MjyModelFactory.makeClass(references);
        }
        
        if (classTypeUsed.isExternal()) {
          // need to copy the imports and external class names for languages
          for (String lang : classTypeUsed.getLangKeySet()) {
            theClass.addLangImport(lang, classTypeUsed.getLangDepResource(lang));
          }
        }

        String collectionType = mappedType.get("collection");
        if (null == collectionType) {
          theClass.addAttribute(
              MjyModelFactory.makeAttribute(member,
                  MjyModelFactory.makeObject(classTypeUsed)));
          continue;
        } else {
          MjyCollectionType collType = MjyCollectionType.getMjyCollectionType(collectionType);
          theClass.addCollection(
              MjyModelFactory.makeCollection(member, collType,
                  MjyModelFactory.makeObject(classTypeUsed)));
          if (MjyCollectionType.ARRAYLIST == collType) {
            theClass.setUsesArrayList(true);
          } else if (MjyCollectionType.HASHMAP == collType) {
            theClass.setUsesMap(true);
          }
          continue;
        }
      }
    }
    return true;
  }

  private boolean cyclicInheritanceFound() throws Exception {
    HashSet<MjyClass> checkSet = new HashSet<>();
    HashSet<MjyClass> alreadyVerified = new HashSet<>();
    
    Recursive<Function<MjyClass, Boolean>>
      foundCyclicInheritance = new Recursive<>();
    
    foundCyclicInheritance.func = (clazz) -> {
      if(!checkSet.add(clazz) ) {
        String msg = "Found cyclic inheritance involving class <" + clazz.getName() + ">";
        log.error(msg);
        return Boolean.TRUE;
      }
//    if (member.equals("import")) {
//    // is an imported class
//    theClass.setImportClass(classMap.get(member).toString());
//    return true;
//  }

      if (alreadyVerified.contains(clazz))
        return Boolean.FALSE;
      alreadyVerified.add(clazz);

      MjyClass gener = clazz.getGeneralization();
      if (null == gener)
        return Boolean.FALSE;
      return foundCyclicInheritance.func.apply(gener);
    };
    
    Iterator<MjyClass> classIt = theModel.getClassIterator();
    while( classIt.hasNext() ) {
      MjyClass clazz = classIt.next();
      checkSet.clear();
      if (Boolean.TRUE == foundCyclicInheritance.func.apply(clazz))
        return true;
    }
    
    return false;
  } 

  public void dumpLoaderInfo() {

    log.info("Dumping loader info: ");

    Collection<? extends Object> keys = modelLoader.getKeys();
    Iterator<? extends Object> it = keys.iterator();
    while (it.hasNext()) {
      Object key = it.next();
      log.info("Found key: " + key.toString());
    }

    Collection<? extends Object> mappings = modelLoader.getMappings();
    it = mappings.iterator();
    while (it.hasNext()) {
      Object key = it.next();
      log.info("Mapping: " + key.toString());
    }
  }
}
