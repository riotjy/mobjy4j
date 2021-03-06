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

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class CppDeserializeObjectCodeGenerator extends SerializeValueCodeGenerator {

  public CppDeserializeObjectCodeGenerator() {
    super();
  }

  public CppDeserializeObjectCodeGenerator(Collection<String> classNames) {
    super(classNames);
  }

  @Override
  public String generate() {
    String code = 
        "  std::shared_ptr<IMjyRoot> deserObject(jsonl::json &  jo) {\n" + 
        "    std::string cnid = jo[\"cnid\"];\n" + 
        "\n";

    for (String className : classNames) {
      code += 
          "    if (cnid == \"" + className + "\") {\n" + 
          "      std::shared_ptr<" + className + "> obj = std::make_shared<" + className + ">();\n" + 
          "      deser" + className + "(jo, obj.get());\n" + 
          "      return obj;\n" + 
          "    }\n";
    }

    code += 
        "    return nullptr;\n" + 
        "  }\n" + 
        "\n";
    return code;
  }

}
