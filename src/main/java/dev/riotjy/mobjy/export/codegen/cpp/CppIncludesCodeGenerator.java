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

import dev.riotjy.mobjy.export.codegen.ImportsCodeGenerator;

public class CppIncludesCodeGenerator extends ImportsCodeGenerator {

  public CppIncludesCodeGenerator() {
    super();
  }

  public CppIncludesCodeGenerator(boolean usesList, boolean usesMap) {
    super(usesList, usesMap);
  }

  @Override
  public String generate() {
    String code = "";
    boolean exIncluded = false;
    if (usesList) {
      code += "#include <stdexcept>\n";
      exIncluded = true;
      code += "#include <vector>\n";
    }
    if (usesMap) {
      if (!exIncluded)
        code += "#include <stdexcept>\n";
      code += "#include <map>\n";
    }

    if (!requiredImports.isEmpty()) {
      for (String impStr : requiredImports) {
        code += "#include " + impStr + "\n";
      }
      code += "\n";
    }

    return code;
  }

}
