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
package dev.riotjy.mobjy.export.codegen.java;

import java.util.HashMap;
import java.util.Iterator;

import dev.riotjy.mobjy.export.codegen.DeserializeClassCodeGenerator;
import dev.riotjy.mobjy.model.MjyObject;
import dev.riotjy.mobjy.model.MjyPrimitive;
import dev.riotjy.mobjy.model.MjyType;

public class JavaDeserializeClassCodeGenerator extends DeserializeClassCodeGenerator {

  public JavaDeserializeClassCodeGenerator() {
    
  }

  public JavaDeserializeClassCodeGenerator(String className, String generalizationName) {
    super(className, generalizationName);
  }

  public JavaDeserializeClassCodeGenerator(String className, String generalizationName,
      HashMap<String, MjyType> attributesInfo, HashMap<String, MjyType> arraysInfo, HashMap<String, MjyType> mapsInfo) {
    super(className, generalizationName, attributesInfo, arraysInfo, mapsInfo);
  }

  @Override
  public String generate() {
    
    String code = 
        "  private static void deser" + className + "(JsonObject jo," + className + " value) {\n";
    if (null == generalizationName &&
        attributesInfo.isEmpty() &&
        arraysInfo.isEmpty() &&
        mapsInfo.isEmpty()) {
      code += "    // TODO: ADD DESERIALIZATION CODE HERE IF NECESSARY\n";
    }
    
    if (null != generalizationName) {
      code += "    deser" + generalizationName + "(jo, value);\n";
    }

    Iterator<String> it = attributesInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      MjyType type = attributesInfo.get(val);
      if (type instanceof MjyPrimitive) {
        code += "    value." + val + " = jo.get(\"" + val + "\").getAs" + getPrmAsTypeStr(type) + "();\n";
      } else {
        String typeName = type.getTypeName();
        code += "    value." + val + " = (" + typeName +")deserObject(jo.get(\"" + val + "\").getAsJsonObject());\n";
      }
    }

    it = arraysInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      String primInfo = getPrmTypeStr(arraysInfo.get(val));
      if ("null" != primInfo)
        primInfo = "MjyPrimitiveType." + primInfo;
      code += "    value." + val + " = deserArr(jo.get(\"" + val + "\").getAsJsonArray(), " + primInfo + ");\n";
    }

    it = mapsInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      String primInfo = getPrmTypeStr(mapsInfo.get(val));
      if ("null" != primInfo)
        primInfo = "MjyPrimitiveType." + primInfo;
      code += "    value." + val + " = deserMap(jo.get(\"" + val + "\").getAsJsonObject(), " + primInfo + ");\n";
    }

    code += "  }\n";
    return code;
 }

  private String getPrmAsTypeStr(MjyType type) {
    if (!(type instanceof MjyPrimitive)) {
      return "String";
    }
    switch(((MjyPrimitive)type).getType()) {
    
    case BOOLEAN:
      return "Boolean";
    case CHAR:
      return "Character";
    case BYTE:
      return "Byte";
    case SHORT:
      return "Short";
    case INT:
      return "Int";
    case LONG:
      return "Long";
    case FLOAT:
      return "Float";
    case DOUBLE:
      return "Double";
    case STRING:
    default:
      return "String";
    }
  }

  private String getPrmTypeStr(MjyType type) {
    if (!(type instanceof MjyPrimitive)) {
      return "null";
    }
    switch(((MjyPrimitive)type).getType()) {
    
    case BOOLEAN:
      return "BOOLEAN";
    case CHAR:
      return "CHAR";
    case BYTE:
      return "BYTE";
    case SHORT:
      return "SHORT";
    case INT:
      return "INT";
    case LONG:
      return "LONG";
    case FLOAT:
      return "FLOAT";
    case DOUBLE:
      return "DOUBLE";
    case STRING:
    default:
      return "STRING";
    }
  }
}
