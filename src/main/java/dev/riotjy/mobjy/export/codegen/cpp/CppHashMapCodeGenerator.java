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

import dev.riotjy.mobjy.export.codegen.HashMapCodeGenerator;

public class CppHashMapCodeGenerator extends HashMapCodeGenerator {

  public CppHashMapCodeGenerator() {
    super();
  }

  public CppHashMapCodeGenerator(String name, String valueType) {
    super(name, valueType);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "public:\n"
        + "  std::map<std::string, " + valueType + "> " + name + ";\n\n";
    // size
    code += "  size_type sizeOf" + capitalized + "() {\n";
    code += "    return this->" + name + ".size();\n";
    code += "  }\n\n";
    // pop front
    code += "  " + valueType + " getFrom" + capitalized + "(String key) {\n";
    code += "    return this->" + name + "[key];\n";
    code += "  }\n\n";
    // push back
    code += "  void putTo" + capitalized + "(String key, " + valueType + " value) {\n";
    code += "    this->" + name + ".emplace(key, value);\n";
    code += "  }\n\n";
    // iterator
    code += "  auto iteratorOf" + capitalized + "() {\n";
    code += "    return this->" + name + ".cbegin();\n";
    code += "  }\n";
    return code;
  }

}
