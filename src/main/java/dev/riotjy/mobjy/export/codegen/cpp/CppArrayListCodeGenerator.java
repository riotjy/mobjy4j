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

import dev.riotjy.mobjy.export.codegen.ArrayListCodeGenerator;

public class CppArrayListCodeGenerator extends ArrayListCodeGenerator {

  public CppArrayListCodeGenerator() {
    super();
  }

  public CppArrayListCodeGenerator(String name, String type) {
    super(name, type);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "public:\n" +
        "  std::vector<" + type + "> " + name + ";\n\n";
    code += "public:\n";
    // size
    code += "  std::vector<" + type + ">::size_type size" + capitalized + "() {\n";
    code += "    return this->" + name + ".size();\n";
    code += "  }\n\n";
    // at(pos)
    code += "  " + type + " & at" + capitalized + "(std::vector<" + type + ">::size_type pos) {\n";
    code += "    if (pos >= " + name + ".size())\n";
    code += "      throw std::invalid_argument(\"Vector position/index out of bounds!\");\n";
    code += "    return this->" + name + ".at(pos);\n";
    code += "  }\n\n";
    // pop front
    code += "  " + type + " popFront" + capitalized + "() {\n";
    code += "    if (0 == " + name + ".size())\n";
    code += "      throw std::length_error(\"Vector is empty!\");\n";
    code += "    " + type + " ret = this->" + name + ".front();\n";
    code += "    this->" + name + ".erase(" + name + ".begin());\n";
    code += "    return ret;\n";
    code += "  }\n\n";
    // push back
    code += "  void pushBack" + capitalized + "(" + type + " value) {\n";
    code += "    this->" + name + ".push_back(value);\n";
    code += "  }\n\n";
    // iterator
    code += "  std::vector<" + type + ">::const_iterator iterator" + capitalized + "() {\n";
    code += "    return this->" + name + ".cbegin();\n";
    code += "  }\n";
    return code;
  }

}
