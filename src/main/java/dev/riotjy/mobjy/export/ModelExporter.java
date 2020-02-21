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
package dev.riotjy.mobjy.export;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.export.codegen.AttributeCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppArrayListCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppExternalClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppHashMapCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppIncludesCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppMetaTypeMap;
import dev.riotjy.mobjy.export.codegen.cpp.CppNamespaceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppObjAttrCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppPrimAttrCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppResourceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppRootInterfaceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaArrayListCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaAttributeCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaHashMapCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaImportsCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaMetaTypeMap;
import dev.riotjy.mobjy.export.codegen.java.JavaPackageCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaPrimitiveTypesStaticCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaResourceCodeGenerator;
import dev.riotjy.mobjy.model.MjyAttribute;
import dev.riotjy.mobjy.model.MjyClass;
import dev.riotjy.mobjy.model.MjyCollection;
import dev.riotjy.mobjy.model.MjyCollectionType;
import dev.riotjy.mobjy.model.MjyModel;
import dev.riotjy.mobjy.model.MjyType;
import dev.riotjy.mobjy.util.ResourceWriter;

public class ModelExporter {

  final static Logger log = LoggerFactory.getLogger(ModelExporter.class);

  private MjyModel theModel;
  
  private ModelExporter() {}
  
  public ModelExporter(MjyModel model) {
    this();
    this.theModel = model;
  }
  
  public void exportJava(String path) {
    Iterator<MjyClass> itClass = theModel.getClassIterator();
    while (itClass.hasNext()) {
      MjyClass clazz = itClass.next();
      
      if (clazz.isExternal()) {
        // no generation for this class
        continue;
      }
      
      MjyClass gener = clazz.getGeneralization();
      JavaClassCodeGenerator classGen = new JavaClassCodeGenerator(clazz.getName(), false, null != gener ? gener.getName() : null);
      int cnt = clazz.getAttributeCount();
      for (int i = 0; i < cnt; ++i) {
        MjyAttribute attr = clazz.getAttributeByIndex(i);
        JavaAttributeCodeGenerator attrGen = new JavaAttributeCodeGenerator(attr.getName(), getJavaTypeName(attr.getType()));
        classGen.addPart(attrGen);
      }
      cnt = clazz.getCollectionCount();
      for (int i = 0; i < cnt; ++i) {
        MjyCollection coll = clazz.getCollectionByIndex(i);
        if (coll.getCollectionType() == MjyCollectionType.ARRAYLIST) {
          JavaArrayListCodeGenerator arrGen = new JavaArrayListCodeGenerator(coll.getName(), getJavaTypeName(coll.getValueType()));
          classGen.addPart(arrGen);
        }
        if (coll.getCollectionType() == MjyCollectionType.HASHMAP) {
          JavaHashMapCodeGenerator hashGen = new JavaHashMapCodeGenerator(coll.getName(), getJavaTypeName(coll.getValueType()));
          classGen.addPart(hashGen);
        }
      }
      JavaImportsCodeGenerator importGen = 
          new JavaImportsCodeGenerator(clazz.usesArrayList(), clazz.usesMap());
      Iterator<String> impIt = clazz.getLangImportsIter("java");
      if (null != impIt) {
        while (impIt.hasNext()) {
          importGen.addImport(impIt.next());
        }
      }
      
      JavaPackageCodeGenerator packGen = new JavaPackageCodeGenerator(
          theModel.getLanguageSettingValue("java", "package"));
      
      JavaResourceCodeGenerator resourceGen = new JavaResourceCodeGenerator(clazz.getName());
      resourceGen.addPart(packGen);
      resourceGen.addPart(importGen);
      resourceGen.addPart(classGen);
      String code = resourceGen.generate();
      log.info("\n********\n" + code + "\n********\n\n");
      ResourceWriter.mkdirs(path + "/java");
      ResourceWriter.write(path + "/java/" + clazz.getName() + ".java", code);
    }

    exportMjyPrimitiveTypes(path);
  }
  
  private void exportMjyPrimitiveTypes(String path) {
    JavaResourceCodeGenerator resourceGen = new JavaResourceCodeGenerator("MjyPrimitiveType");

    JavaPackageCodeGenerator packGen = new JavaPackageCodeGenerator(
        theModel.getLanguageSettingValue("java", "package"));
    resourceGen.addPart(packGen);
    resourceGen.addPart(new JavaPrimitiveTypesStaticCodeGenerator());
    
    String code = resourceGen.generate();
    log.info("\n********\n" + code + "\n********\n\n");
    path += "/java";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/MjyPrimitiveType.java", code);
  }
  
  private String getJavaTypeName(MjyType type) {
    if (MjyType.isObject(type)) {
      String typeName = type.getTypeName();
      MjyClass clazz = theModel.getClassByName(typeName);
      if (null != clazz && clazz.isExternal()) {
        typeName = clazz.getLangDepClass("java");
      }
      return typeName;
    }
    return JavaMetaTypeMap.instance().get(type.getTypeName());
  }

  public void exportCpp(String path) {
    generateRootInterfaceCppCode(path);
    Iterator<MjyClass> itClass = theModel.getClassIterator();
    while (itClass.hasNext()) {
      MjyClass clazz = itClass.next();

      if (clazz.isExternal()) {
        generateExtClassCppCode(clazz, path);
      } else {
        generateClassCppCode(clazz, path);
      }
    }
  }
  
  private void generateClassCppCode(MjyClass clazz, String path) {
    CppIncludesCodeGenerator includeGen = 
        new CppIncludesCodeGenerator(clazz.usesArrayList(), clazz.usesMap());
    Iterator<String> impIt = clazz.getLangImportsIter("cpp");
    if (null != impIt) {
      while (impIt.hasNext()) {
        includeGen.addImport(impIt.next());
      }
    }

    MjyClass gener = clazz.getGeneralization();
    CppClassCodeGenerator classGen = new CppClassCodeGenerator(clazz.getName(), false, null != gener ? gener.getName() : null);
    
    boolean usesMemory = false;
    
    int cnt = clazz.getAttributeCount();
    for (int i = 0; i < cnt; ++i) {
      MjyAttribute attr = clazz.getAttributeByIndex(i);
      MjyType attrType = attr.getType();
      AttributeCodeGenerator attrGen;
      if (MjyType.isPrimitive(attrType)) {
        attrGen = new CppPrimAttrCodeGenerator(attr.getName(), getCppTypeName(attrType));
      } else {
        attrGen = new CppObjAttrCodeGenerator(attr.getName(), getCppTypeName(attrType));
        if (!usesMemory) {
          includeGen.addImport("<memory>");
          usesMemory = true;
        }
      }
      classGen.addPart(attrGen);
      String needImp = getCppIncludeIfNeed(attrType);
      if (null != needImp) {
        includeGen.addImport(needImp);
      }
    }
    
    cnt = clazz.getCollectionCount();
    for (int i = 0; i < cnt; ++i) {
      MjyCollection coll = clazz.getCollectionByIndex(i);
      MjyType collValType = coll.getValueType();
      String cppTypeName = getCppTypeName(collValType);
      if (MjyType.isObject(collValType)) {
        cppTypeName = "std::shared_ptr<" + cppTypeName + ">";
        if (!usesMemory) {
          includeGen.addImport("<memory>");
          usesMemory = true;
        }
      }
      if (coll.getCollectionType() == MjyCollectionType.ARRAYLIST) {
        CppArrayListCodeGenerator arrGen = new CppArrayListCodeGenerator(coll.getName(), cppTypeName);
        classGen.addPart(arrGen);
      }
      if (coll.getCollectionType() == MjyCollectionType.HASHMAP) {
        CppHashMapCodeGenerator hashGen = new CppHashMapCodeGenerator(coll.getName(), cppTypeName);
        classGen.addPart(hashGen);
      }
      String needImp = getCppIncludeIfNeed(collValType);
      if (null != needImp) {
        includeGen.addImport(needImp);
      }
    }

    if (null != gener) {
      includeGen.addImport("\"" + gener.getName() + ".hpp\"");
    } else {
      includeGen.addImport("\"IMjyRoot.hpp\"");
    }

    CppNamespaceCodeGenerator namspGen =
        new CppNamespaceCodeGenerator(theModel.getLanguageSettingValue("cpp", "namespace"));
    namspGen.addPart(classGen);
    
    CppResourceCodeGenerator resourceGen = new CppResourceCodeGenerator(clazz.getName());
    resourceGen.addPart(includeGen);
    resourceGen.addPart(namspGen);
    String code = resourceGen.generate();
    log.info("\n+++++++++++\n" + code + "\n+++++++++++\n\n");
    path += "/cpp";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/" + clazz.getName() + ".hpp", code);
  }

  private void generateExtClassCppCode(MjyClass clazz, String path) {

    CppIncludesCodeGenerator includeGen = 
        new CppIncludesCodeGenerator(false, false);
    
    includeGen.addImport(clazz.getLangDepResource("cpp"));

    String className = clazz.getName();
    String genName = clazz.getLangDepClass("cpp");
    CppExternalClassCodeGenerator classGen = 
        new CppExternalClassCodeGenerator(className, false, 
            genName);
    
    CppNamespaceCodeGenerator namspGen =
        new CppNamespaceCodeGenerator(theModel.getLanguageSettingValue("cpp", "namespace"));
    namspGen.addPart(classGen);
    
    CppResourceCodeGenerator resourceGen = new CppResourceCodeGenerator(clazz.getName());

    resourceGen.addPart(includeGen);
    resourceGen.addPart(namspGen);
    String code = resourceGen.generate();
    log.info("\n+++++++++++\n" + code + "\n+++++++++++\n\n");
    path += "/cpp";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/" + clazz.getName() + ".hpp", code);
  }

  private void generateRootInterfaceCppCode(String path) {
    CppResourceCodeGenerator resourceGen = new CppResourceCodeGenerator("IMjyRoot");
    resourceGen.addPart(new CppRootInterfaceCodeGenerator());
    String code = resourceGen.generate();
    log.info("\n+++++++++++\n" + code + "\n+++++++++++\n\n");
    path += "/cpp";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/IMjyRoot.hpp", code);
  }
  
  private String getCppTypeName(MjyType type) {
    if (MjyType.isObject(type)) {
      String typeName = type.getTypeName();
      MjyClass clazz = theModel.getClassByName(typeName);
      if (null != clazz && clazz.isExternal()) {
        typeName = clazz.getLangDepClass("cpp");
      }
      return typeName;
    }
    return CppMetaTypeMap.instance().get(type.getTypeName());
  }
  
  private String getCppIncludeIfNeed(MjyType type) {
    if (MjyType.isObject(type)) {
      String typeName = type.getTypeName();
      if (!theModel.getClassByName(typeName).isExternal()) {
        return "\"" + typeName + ".hpp\"";
      }
    }    
    return null;
  }
}
