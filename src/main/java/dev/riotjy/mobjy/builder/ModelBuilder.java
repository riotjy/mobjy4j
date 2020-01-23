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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.model.MjyCollectionType;
import dev.riotjy.mobjy.model.MjyModel;
import dev.riotjy.mobjy.model.MjyModelFactory;
import dev.riotjy.mobjy.model.MjyPrimitiveType;
import dev.riotjy.mobjy.model.MjyVersion;

public class ModelBuilder {

  final static Logger log = LoggerFactory.getLogger(ModelBuilder.class);

  private ModelLoader modelLoader;
  private MjyModel theModel;

  public ModelBuilder() {
  }

  public ModelBuilder(String fileName) {
    modelLoader = ModelLoader.getLoadedLoader(fileName);
  }

  public boolean build() throws Exception {
    buildModel();
    buildClasses();
    buildInheritance();
    checkCyclicInheritance();
    buildMembers();
    return true;
  }

  public boolean buildModel() throws Exception {
    String project = modelLoader.getMapped("project").toString();
    Map ver = (Map)modelLoader.getMapped("version");
    Map comver = (Map)modelLoader.getMapped("version");
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
  public boolean buildClasses() throws Exception {
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
  
  public boolean buildInheritance() throws Exception {
    Collection<? extends Object> keys = modelLoader.getKeys();
    for (Object key : keys) {
      if (key.toString().equals("project") ||
          key.toString().equals("version") ||
          key.toString().equals("compatver") ) {
          continue;
      }
      String className = key.toString();
      String extendsClass = (String)((Map)modelLoader.getMapped(key.toString())).get("extends");
      theModel.getClassByName(className).setGeneralization(theModel.getClassByName(extendsClass));
    }
    return true;
  }
  
  public boolean checkCyclicInheritance() throws Exception {
    //TODO: recursive search up the inheritance tree for each derived class
    // mark nodes already checked
    return true;
  }
  
  public boolean buildMembers() throws Exception {
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
  
  public boolean buildMembers(String className) throws Exception {
    Object classMap = (Map)modelLoader.getMapped(className);
    Set<String> members = ((Map)classMap).keySet();
    for (String member : members) {
      Object type = ((Map)classMap).get(member);
      if (type instanceof String) {
        if (type.toString().contains("[]")) {
          theModel.getClassByName(className).addCollection(
              MjyModelFactory.makeCollection(member,
                  MjyCollectionType.ARRAYLIST,
                  MjyModelFactory.makePrimitive(
                      MjyPrimitiveType.getMjyPrimitiveType(type.toString().replace("[]", "")))));
        } else {
          theModel.getClassByName(className).addAttribute(
              MjyModelFactory.makeAttribute(member,
                  MjyModelFactory.makePrimitive(
                      MjyPrimitiveType.getMjyPrimitiveType(type.toString()))));
        }
      }
      if (type instanceof Map) {
        //TODO: add the objects, handle collections
      }
    }
    return true;
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
