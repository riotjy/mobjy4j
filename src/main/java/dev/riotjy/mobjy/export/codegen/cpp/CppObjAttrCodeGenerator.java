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

import dev.riotjy.mobjy.export.codegen.AttributeCodeGenerator;

public class CppObjAttrCodeGenerator extends AttributeCodeGenerator {

  public CppObjAttrCodeGenerator() {
    super();
  }

  public CppObjAttrCodeGenerator(String name, String type) {
    super(name, type);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "protected:\n" +
        "  std::shared_ptr<" + type + "> " + name + " = nullptr;\n";
    code += "public:\n";
    // getter
    code += "  std::shared_ptr<" + type + "> get" + capitalized + "() {\n";
    code += "    return this->" + name + ";\n";
    code += "  }\n\n";
    // setter
    code += "  void set" + capitalized + "(std::shared_ptr<" + type + "> " + name + ") {\n";
    code += "    this->" + name + " = " + name + ";\n";
    code += "  }\n";

    return code;
  }

  
}
