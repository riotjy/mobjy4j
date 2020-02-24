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

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaSerializeStaticCodeGenerator extends CodeGenerator {

  public JavaSerializeStaticCodeGenerator() {
  }

  @Override
  public String generate() {
    return 
        "  private static String serClass(String className, String fields) {\n" + 
        "    String code = \"{\\\"cnid\\\":\" + qtd(className) + \"\";\n" + 
        "    if (null != fields && !fields.isEmpty()) {\n" + 
        "      code += \",\" + fields;\n" + 
        "    }\n" + 
        "    code += \"}\";\n" + 
        "    return code;\n" + 
        "  }\n" + 
        "\n" + 
        "  private static String serArr(ArrayList<?> arr) {\n" + 
        "    String json = \"[\";\n" + 
        "    Iterator<?> it = arr.iterator();\n" + 
        "    \n" + 
        "    while (it.hasNext()) {\n" + 
        "      Object val = it.next();\n" + 
        "      if (it.hasNext()) {\n" + 
        "        json += lin(serValue(val)); \n" + 
        "      } else {\n" + 
        "        json += serValue(val); \n" + 
        "      }\n" + 
        "    }\n" + 
        "    \n" + 
        "    json += \"]\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "\n" + 
        "  private static String serMap(HashMap<String, ?> hm) {\n" + 
        "    String json = \"{\";\n" + 
        "\n" + 
        "    Iterator<String> it = hm.keySet().iterator();\n" + 
        "   \n" + 
        "    while (it.hasNext()) {\n" + 
        "      String key = it.next();\n" + 
        "      Object val = hm.get(key);\n" + 
        "      if (it.hasNext()) {\n" + 
        "        json += lin(con(qtd(key), serValue(val))); \n" + 
        "      } else {\n" + 
        "        json += con(qtd(key), serValue(val)); \n" + 
        "      }\n" + 
        "    }\n" + 
        "    \n" + 
        "    json += \"}\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "  \n" + 
        "  private static String qtd(String in) {\n" + 
        "    return \"\\\"\" + in + \"\\\"\";\n" + 
        "  }\n" + 
        "  private static String con(String first, String second) {\n" + 
        "    return first + \":\" + second;\n" + 
        "  }\n" + 
        "  private static String lin(String in) {\n" + 
        "    return in + \",\";\n" + 
        "  }\n" + 
        "  private static String crl(String in) {\n" + 
        "    return \"{\" + in + \"}\";\n" + 
        "  }\n" + 
        "  \n" + 
        "  public static String serialize(Object value) {\n" + 
        "    return serValue(value);\n" + 
        "  }\n";
  }

}
