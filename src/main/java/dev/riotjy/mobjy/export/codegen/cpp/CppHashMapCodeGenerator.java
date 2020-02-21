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
    code += "public:\n";
    // size
    code += "  std::map<std::string, " + valueType + ">::size_type size" + capitalized + "() {\n";
    code += "    return this->" + name + ".size();\n";
    code += "  }\n\n";
    // get
    code += "  " + valueType + " & get" + capitalized + "(std::string key) {\n";
    code += "    return this->" + name + "[key];\n";
    code += "  }\n\n";
    // put (using std::map::emplace)
    code += "  bool put" + capitalized + "(std::string key, " + valueType + " value) {\n";
    code += "    auto retPair = this->" + name + ".emplace(key, value);\n";
    code += "    return retPair.second;\n";
    code += "  }\n\n";
    // remove
    code += "  std::map<std::string, " + valueType + ">::size_type erase" + capitalized + "(std::string key) {\n";
    code += "    return this->" + name + ".erase(key);\n";
    code += "  }\n\n";
    // iterator
    code += "  std::map<std::string, " + valueType + ">::const_iterator iterator" + capitalized + "() {\n";
    code += "    return this->" + name + ".cbegin();\n";
    code += "  }\n";
    return code;
  }

}
