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

import dev.riotjy.mobjy.export.codegen.ClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class CppClassCodeGenerator extends ClassCodeGenerator {

  public CppClassCodeGenerator() {
    super();
  }

  public CppClassCodeGenerator(String className, boolean isAbstract, String generalization) {
    super(className, isAbstract, generalization);
  }

  @Override
  public String generate() {
    String code = "class " +
        className +
        (null == generalization ? "" : ": public " + generalization) +
        " {\n";

    for (CodeGenerator part : parts) {
      code += part.generate() + "\n";
    }

    code += "}; // class " + className + "\n\n";
    return code;
  }

}