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

import dev.riotjy.mobjy.export.codegen.ResourceCodeGenerator;

public class CppResourceCodeGenerator extends ResourceCodeGenerator {

  public CppResourceCodeGenerator() {
    super();
  }

  public CppResourceCodeGenerator(String resourceName) {
    super(resourceName);
  }

  @Override
  public String generate() {

    String code = 
        "#include <cstdint>\n" + 
        "#include <string>\n";
    
    code += parts.get(0).generate() + "\n";
    
    code += "#pragma once\n" +
        "#ifndef " + resourceName + "_hpp\n" +
        "#define " + resourceName + "_hpp\n\n";

    for (int idx = 1; idx < parts.size(); ++idx) {
      code += parts.get(idx).generate() + "\n";
    }
//    for (CodeGenerator part : parts) {
//      code += part.generate() + "\n";
//    }
    
    code +=
        "#endif // " + resourceName + "_hpp\n";

    return code;
  }

}
