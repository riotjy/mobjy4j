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
    String code = "public:" +
        "  std::vector<" + type + "> " + name + ";\n\n";
    // size
    code += "  size_type sizeOf" + capitalized + "() {\n";
    code += "    return this->" + name + ".size();\n";
    code += "  }\n\n";
    // pop front
    code += "  " + type + " popFrontFrom" + capitalized + "() {\n";
    code += "    if (0 == " + name + ".size())\n";
    code += "      return null;\n";
    code += "    " + type + " ret = this->" + name + ".front();\n";
    code += "    this->" + name + ".erase(" + name + ".begin());\n";
    code += "    return ret;\n";
    code += "  }\n\n";
    // push back
    code += "  void pushBackTo" + capitalized + "(" + type + " value) {\n";
    code += "    this->" + name + ".push_back(value);\n";
    code += "  }\n\n";
    // iterator
    code += "  auto iteratorOf" + capitalized + "() {\n";
    code += "    return this->" + name + ".cbegin();\n";
    code += "  }\n";
    return code;
  }

}
