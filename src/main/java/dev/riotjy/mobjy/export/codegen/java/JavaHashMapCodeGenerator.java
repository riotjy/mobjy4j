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

import dev.riotjy.mobjy.export.codegen.HashMapCodeGenerator;

public class JavaHashMapCodeGenerator extends HashMapCodeGenerator {

  public JavaHashMapCodeGenerator() {
    super();
  }

  public JavaHashMapCodeGenerator(String name, String valueType) {
    super(name, valueType);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "  public HashMap<String, " + valueType + "> " + name + " = new HashMap<>();\n\n";
    // size
    code += "  public int sizeOf" + capitalized + "() {\n";
    code += "    return this." + name + ".size();\n";
    code += "  }\n\n";
    // pop front
    code += "  public " + valueType + " getFrom" + capitalized + "(String key) {\n";
    code += "    return this." + name + ".get(key);\n";
    code += "  }\n\n";
    // push back
    code += "  public void putTo" + capitalized + "(String key, " + valueType + " value) {\n";
    code += "    this." + name + ".put(key, value);\n";
    code += "  }\n\n";
    // iterator
    code += "  public Set<String> keySetOf" + capitalized + "() {\n";
    code += "    return this." + name + ".keySet();\n";
    code += "  }\n";
    return code;
  }

}
