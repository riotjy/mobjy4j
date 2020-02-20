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

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class CppSerializeObjValueCodeGenerator extends SerializeValueCodeGenerator {

  public CppSerializeObjValueCodeGenerator() {
  }

  public CppSerializeObjValueCodeGenerator(Collection<String> classNames) {
    super(classNames);
  }

  @Override
  public String generate() {
    String code =
        "private:\n" +
        "  std::string serCValue(IMjyRoot* val) {\n"; 

    for (String className : classNames) {
      code += 
          "    if(val->className() == \"" + className + "\") {\n" + 
          "        return serClass(val->className(), ser" + className + "(dynamic_cast<" + className + ">(val)));\n" +
          "    }\n";
    }
    
    code += 
        "  }\n";
    return code;
  }

}
