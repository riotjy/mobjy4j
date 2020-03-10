/*******************************************************************************
 * Copyright 2020 riotjy and listed authors
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
 *    
 *    Authors:
 *      Alex Savulov
 *******************************************************************************/
package dev.riotjy.mobjy.export;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.export.codegen.cpp.CppClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppIncludesCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppNamespaceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppResourceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppSerializeClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppSerializeObjValueCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppSerializerStaticCodeGenerator;
import dev.riotjy.mobjy.export.codegen.cpp.CppValCat;
import dev.riotjy.mobjy.export.codegen.java.JavaClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaImportsCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaPackageCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaResourceCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeStaticCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaSerializeValueCodeGenerator;
import dev.riotjy.mobjy.model.MjyClass;
import dev.riotjy.mobjy.model.MjyModel;
import dev.riotjy.mobjy.model.MjyPrimitive;
import dev.riotjy.mobjy.model.MjyPrimitiveType;
import dev.riotjy.mobjy.model.MjyType;
import dev.riotjy.mobjy.util.ResourceWriter;

public class SerializeExporter {
  
  final static Logger log = LoggerFactory.getLogger(SerializeExporter.class);

  MjyModel theModel;

  private SerializeExporter() {}

  public SerializeExporter(MjyModel model) {
    super();
    theModel = model;
  }
  
  public void exportJava (String path) {

    JavaResourceCodeGenerator resourceGen = new JavaResourceCodeGenerator(theModel.getProject() + "Serializer");

    resourceGen.addPart(new JavaPackageCodeGenerator(theModel.getLanguageSettingValue("java", "package")));

    JavaImportsCodeGenerator importGen = 
        new JavaImportsCodeGenerator(true, true);
    importGen.addImport("java.util.Iterator");
    resourceGen.addPart(importGen);

    String capitalized = theModel.getProject().substring(0,1).toUpperCase() + theModel.getProject().substring(1);
    JavaClassCodeGenerator classGen = new JavaClassCodeGenerator(capitalized + "Serializer", false, null);
    
    JavaSerializeValueCodeGenerator valGen = new JavaSerializeValueCodeGenerator();

    for (MjyClass clazz : theModel.getClasses()) {
      String className = clazz.getName();
      if (clazz.isExternal()) {
        importGen.addImport(clazz.getLangDepResource("java"));
      }
      
      valGen.addClassName(className);
      
      MjyClass gener = clazz.isExternal() ? null : clazz.getGeneralization();
      
      JavaSerializeClassCodeGenerator clsGen  = 
          new JavaSerializeClassCodeGenerator(className, (null != gener) ? gener.getName() : null,
            clazz.getAttributeNames(), clazz.getArrayNames(), clazz.getMapNames());
      classGen.addPart(clsGen);
    }
    classGen.addPart(valGen);
    classGen.addPart(new JavaSerializeStaticCodeGenerator());
    resourceGen.addPart(classGen);

    String code = resourceGen.generate();
    log.info("\n$$$$$$$$$$$$$$$$$$$$$$$\n" + code + "\n$$$$$$$$$$$$$$$$$$$$$$$\n\n");
    path += "/java";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/" + capitalized + "Serializer" + ".java", code);
  }
  
  public void exportCpp(String path) {
    CppResourceCodeGenerator resourceGen = 
        new CppResourceCodeGenerator(
            theModel.getProject().substring(0,1).toUpperCase() +
            theModel.getProject().substring(1) + 
            "Serializer");
    
    CppIncludesCodeGenerator includeGen = new CppIncludesCodeGenerator(true, true);
    includeGen.addImport("<iterator>");
    includeGen.addImport("\"json.hpp\"");

    String capitalized = theModel.getProject().substring(0,1).toUpperCase() + theModel.getProject().substring(1);
    CppClassCodeGenerator classGen = new CppClassCodeGenerator(capitalized+"Serializer", false, null);
    
    CppSerializeObjValueCodeGenerator valGen = new CppSerializeObjValueCodeGenerator();
    for (MjyClass clazz : theModel.getClasses()) {
      String className = clazz.getName();
      includeGen.addImport("\"" + className + ".hpp\"");
      
      valGen.addClassName(className);
      
      MjyClass gener = clazz.getGeneralization();
      
      CppSerializeClassCodeGenerator clsGen  = 
          new CppSerializeClassCodeGenerator(className, (null != gener) ? gener.getName() : null,
              getFieldsInfo(clazz.getAttributesInfo()), getFieldsInfo(clazz.getArraysInfo()), 
              getFieldsInfo(clazz.getMapsInfo()));
      classGen.addPart(clsGen);
    }
    
    classGen.addPart(valGen);
    classGen.addPart(new CppSerializerStaticCodeGenerator());

    CppNamespaceCodeGenerator namspGen =
        new CppNamespaceCodeGenerator(theModel.getLanguageSettingValue("cpp", "namespace"));
    namspGen.addPart(classGen);

    resourceGen.addPart(includeGen);
    resourceGen.addPart(namspGen);
    
    String code = resourceGen.generate();
    log.info("\n@@@@@@@@@@@@@@@@@@@@\n" + code + "\n@@@@@@@@@@@@@@@@@@@@\n\n");
    path += "/cpp";
    ResourceWriter.mkdirs(path);
    ResourceWriter.write(path + "/" + capitalized + "Serializer" + ".hpp", code);
  }
  
  private HashMap<String, CppValCat> getFieldsInfo(HashMap<String, MjyType> fieldsInfo)  {
    HashMap<String, CppValCat> retFieldsInfo = new HashMap<String, CppValCat>();
    Set<String> fieldNames = fieldsInfo.keySet();
    for (String fieldName : fieldNames) {
      CppValCat valCat = CppValCat.INVALID;
      if (MjyType.isPrimitive(fieldsInfo.get(fieldName))) {
        MjyPrimitive mjyPrim = (MjyPrimitive) fieldsInfo.get(fieldName);
        if (mjyPrim.getType() == MjyPrimitiveType.STRING) {
          valCat = CppValCat.STRING;
        } else if(mjyPrim.getType() == MjyPrimitiveType.BOOLEAN) {
          valCat = CppValCat.BOOLEAN;
        } else {
          valCat = CppValCat.PRIMITIVE;
        }
      } else if (MjyType.isObject(fieldsInfo.get(fieldName))) {
        valCat = CppValCat.OBJECT;
      }
      retFieldsInfo.put(fieldName, valCat);
    }
    return retFieldsInfo;
  }
}
