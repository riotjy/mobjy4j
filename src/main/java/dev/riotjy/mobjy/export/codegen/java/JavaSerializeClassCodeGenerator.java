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
package dev.riotjy.mobjy.export.codegen.java;

import java.util.ArrayList;
import java.util.Iterator;

import dev.riotjy.mobjy.export.codegen.SerializeClassCodeGenerator;

public class JavaSerializeClassCodeGenerator extends SerializeClassCodeGenerator {

  public JavaSerializeClassCodeGenerator() {
  }

  public JavaSerializeClassCodeGenerator(String className, String generalizationName) {
    super(className, generalizationName);
  }

  public JavaSerializeClassCodeGenerator(String className, String generalizationName, ArrayList<String> attributeNames,
      ArrayList<String> arrayNames, ArrayList<String> mapNames) {
    super(className, generalizationName, attributeNames, arrayNames, mapNames);
  }

  @Override
  public String generate() {
    
    boolean hasAttributes = !attributeNames.isEmpty();
    boolean hasArrays = !arrayNames.isEmpty();
    boolean hasMaps = !mapNames.isEmpty();
    boolean hasFields = hasAttributes || hasArrays || hasMaps;
    
    String code = 
        "  private static String ser" + className + "(" + className + " value) {\n" +
        "    return\n";
    
    if (null != generalizationName) {
      code += "        ";
      if (hasFields) {
        code += "lin(";
      }
      code += "ser" + generalizationName + "(" + generalizationName + " value)"; 
      if (hasFields) {
        code += ") +\n";
      }
    } else if (!hasFields) {
      code += 
          "        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS " + className + "\n" +
          "        \"\"";
      code += ";\n  }\n";
      return code;
    }

    Iterator<String> it = attributeNames.iterator();
    while (it.hasNext()) {
      String val = it.next();
      code += "        ";
      if (it.hasNext() || hasArrays || hasMaps) {
        code += "lin(con(qtd(" + val + "), serValue(value." + val + "))) +\n";
      } else {
        code += "con(qtd(" + val + "), serValue(value." + val + "))";
      }
    }

    it = arrayNames.iterator();
    while (it.hasNext()) {
      String val = it.next();
      code += "        ";
      if (it.hasNext() || hasMaps) {
        code += "lin(con(qtd(" + val + "), serArr(value." + val + "))) +\n";
      } else {
        code += "con(qtd(" + val + "), serArr(value." + val + "))";
      }
    }

    it = mapNames.iterator();
    while (it.hasNext()) {
      String val = it.next();
      code += "        ";
      if (it.hasNext()) {
        code += "lin(con(qtd(" + val + "), serMap(value." + val + "))) +\n";
      } else {
        code += "con(qtd(" + val + "), serMap(value." + val + "))";
      }
    }

    code += ";\n  }\n";
    return code;
  }

}
