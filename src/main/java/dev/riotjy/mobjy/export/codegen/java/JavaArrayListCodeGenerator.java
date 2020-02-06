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

import dev.riotjy.mobjy.export.codegen.ArrayListCodeGenerator;

public class JavaArrayListCodeGenerator extends ArrayListCodeGenerator {

  public JavaArrayListCodeGenerator() {
    super();
  }

  public JavaArrayListCodeGenerator(String name, String type) {
    super(name, type);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "  public List<" + type + "> " + name + " = new ArrayList<>();\n\n";
    // size
    code += "  public int sizeOf" + capitalized + "() {\n";
    code += "    return this." + name + ".size();\n";
    code += "  }\n\n";
    // pop front
    code += "  public " + type + " popFrontFrom" + capitalized + "() {\n";
    code += "    if (0 == " + name + ".size())\n";
    code += "      return null;\n";
    code += "    return this." + name + ".remove(0);\n";
    code += "  }\n\n";
    // push back
    code += "  public void pushBackTo" + capitalized + "(" + type + " value) {\n";
    code += "    this." + name + ".add(value);\n";
    code += "  }\n\n";
    // iterator
    code += "  public Iterator iteratorOf" + capitalized + "() {\n";
    code += "    return this." + name + ".iterator();\n";
    code += "  }\n";
    return code;
  }

}
