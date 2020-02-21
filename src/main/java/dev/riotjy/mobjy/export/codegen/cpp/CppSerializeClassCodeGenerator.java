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
package dev.riotjy.mobjy.export.codegen.cpp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import dev.riotjy.mobjy.export.codegen.SerializeClassCodeGenerator;

public class CppSerializeClassCodeGenerator extends SerializeClassCodeGenerator {

  public CppSerializeClassCodeGenerator() {
  }

  protected HashMap<String, CppValCat> attributesInfo = new HashMap<String, CppValCat>();
  protected HashMap<String, CppValCat> arraysInfo = new HashMap<String, CppValCat>();
  protected HashMap<String, CppValCat> mapsInfo = new HashMap<String, CppValCat>();

  public CppSerializeClassCodeGenerator(String className, String generalizationName, 
      HashMap<String, CppValCat> attributeNames, HashMap<String, CppValCat> arrayNames, 
      HashMap<String, CppValCat> mapNames) {
    this(className, generalizationName);
    this.attributesInfo = attributeNames;
    this.arraysInfo = arrayNames;
    this.mapsInfo = mapNames;
  }

  public CppSerializeClassCodeGenerator(String className, String generalizationName) {
    super(className, generalizationName);
  }

  @Override
  public String generate() {
    boolean hasAttributes = !attributesInfo.isEmpty();
    boolean hasArrays = !arraysInfo.isEmpty();
    boolean hasMaps = !mapsInfo.isEmpty();
    boolean hasFields = hasAttributes || hasArrays || hasMaps;
    
    String code = 
        "private:\n" +
        "  std::string ser" + className + "(" + className + " * value) {\n" +
        "    return\n";
    
    if (null != generalizationName) {
      code += "        ";
      if (hasFields) {
        code += "lin(";
      }
      code += "ser" + generalizationName + "(value)"; 
      if (hasFields) {
        code += ") +\n";
      }
    } else if (!hasFields) {
      code += 
          "        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS " + className + " AS NECESSARY! VERIFY CLASS DEFINITION.\n" +
          "        \"\"";
      code += ";\n  }\n";
      return code;
    }

    Iterator<String> it = attributesInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      String serFuncCall = "std::to_string(value->" + val + ")";
      if (attributesInfo.get(val) == CppValCat.OBJECT)
        serFuncCall = "serCValue(value->" + val + ".get())";
      if (attributesInfo.get(val) == CppValCat.STRING)
        serFuncCall = "value->" + val;
      code += "        ";
      if (it.hasNext() || hasArrays || hasMaps) {
        code += "lin(con(qtd(\"" + val + "\"), " + serFuncCall + ")) +\n";
      } else {
        code += "con(qtd(\"" + val + "\"), " + serFuncCall + ")";
      }
    }

    it = arraysInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      String serFuncName = "serArr";
      if (mapsInfo.get(val) == CppValCat.PRIMITIVE)
        serFuncName = "serPrimArr";
      if (mapsInfo.get(val) == CppValCat.STRING)
        serFuncName = "serStrArr";
      code += "        ";
      if (it.hasNext() || hasMaps) {
        code += "lin(con(qtd(\"" + val + "\"), " + serFuncName + "(value->" + val + "))) +\n";
      } else {
        code += "con(qtd(\"" + val + "\"), " + serFuncName + "(value->" + val + "))";
      }
    }

    it = mapsInfo.keySet().iterator();
    while (it.hasNext()) {
      String val = it.next();
      String serFuncName = "serMap";
      if (mapsInfo.get(val) == CppValCat.PRIMITIVE)
        serFuncName = "serPrimMap";
      if (mapsInfo.get(val) == CppValCat.STRING)
        serFuncName = "serStrMap";
      code += "        ";
      if (it.hasNext()) {
        code += "lin(con(qtd(\"" + val + "\"), " + serFuncName + "(value->" + val + "))) +\n";
      } else {
        code += "con(qtd(\"" + val + "\"), " + serFuncName + "(value->" + val + "))";
      }
    }

    code += ";\n  }\n";
    return code;
  }

}
