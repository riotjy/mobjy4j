/*******************************************************************************
 * Copyright 2020 riotjy and listed authors
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
 *    
 *    Authors:
 *      Alex Savulov
 *******************************************************************************/
package dev.riotjy.mobjy.export.codegen.cpp;

import dev.riotjy.mobjy.export.codegen.ClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class CppExternalClassCodeGenerator extends ClassCodeGenerator {

  public CppExternalClassCodeGenerator() {
    super();
  }

  public CppExternalClassCodeGenerator(String className, boolean isAbstract, String generalization) {
    super(className, isAbstract, generalization);
  }

  @Override
  public String generate() {
    String code = "class " + className + " : public IMjyRoot, " + generalization + " {\n";

    code += "public:\n" +
        "  virtual std::string className() {" +
        "return \"" + className + "\";" +
        "}\n\n";
    
    for (CodeGenerator part : parts) {
      code += part.generate() + "\n";
    }

    code += "  // TODO: ADD ANY NECESSARY CODE FOR DE-/SERIALIZATION OF " + generalization +"\n\n";
    code += "}; // class " + className + "\n\n";
    return code;
  }

}
