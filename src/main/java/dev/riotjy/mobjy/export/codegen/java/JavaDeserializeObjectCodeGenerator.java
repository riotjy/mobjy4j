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

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class JavaDeserializeObjectCodeGenerator extends SerializeValueCodeGenerator {

  public JavaDeserializeObjectCodeGenerator() {
    super();
  }

  public JavaDeserializeObjectCodeGenerator(Collection<String> classNames) {
    super(classNames);
  }

  @Override
  public String generate() {
    String code = 
        "  public static Object deserObject(JsonObject jo) {\n" + 
        "    \n" + 
        "    String cnid = jo.get(\"cnid\").getAsString();\n" + 
        "    \n" + 
        "    switch (cnid) {\n";

    for (String className : classNames) {
      code += 
          "    case \"" + className + "\":\n" + 
          "      " + className + " obj = new " + className + "();\n" +
          "      deser" + className + "(jo ,obj);\n" +
          "      return obj;\n";
    }

    code +=
        "    default:\n" + 
        "      return null;\n" + 
        "    }\n" + 
        "  }\n";
    return code;
  }

}
