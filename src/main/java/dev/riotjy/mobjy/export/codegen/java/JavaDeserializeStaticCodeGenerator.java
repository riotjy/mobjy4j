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
package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaDeserializeStaticCodeGenerator extends CodeGenerator {

  public JavaDeserializeStaticCodeGenerator() {}

  @Override
  public String generate() {
    return 
        "  private static Object deserPrimitive(JsonPrimitive jp, MjyPrimitiveType prType) {\n" + 
        "    if (null == prType) {\n" + 
        "      return jp.getAsString();\n" + 
        "    }\n" + 
        "    switch (prType) {\n" + 
        "    case BOOLEAN:\n" + 
        "      return Boolean.valueOf(jp.getAsBoolean());\n" + 
        "    case CHAR:\n" + 
        "      return Character.valueOf(jp.getAsCharacter());\n" + 
        "    case BYTE:\n" + 
        "      return Byte.valueOf(jp.getAsByte());\n" + 
        "    case SHORT:\n" + 
        "      return Short.valueOf(jp.getAsShort());\n" + 
        "    case INT:\n" + 
        "      return Integer.valueOf(jp.getAsInt());\n" + 
        "    case LONG:\n" + 
        "      return Long.valueOf(jp.getAsLong());\n" + 
        "    case FLOAT:\n" + 
        "      return Float.valueOf(jp.getAsFloat());\n" + 
        "    case DOUBLE:\n" + 
        "      return Double.valueOf(jp.getAsDouble());\n" + 
        "    case STRING:\n" + 
        "      return jp.getAsString();\n" + 
        "    }\n" + 
        "    return null;\n" + 
        "  }\n" + 
        "\n" + 
        "  private static ArrayList deserArr(JsonArray ja, MjyPrimitiveType prType) {\n" + 
        "    ArrayList<Object> arr = new ArrayList<Object>();\n" + 
        "    \n" + 
        "    for (JsonElement el : ja) {\n" + 
        "      if (el.isJsonObject())\n" + 
        "      {\n" + 
        "        arr.add(deserObject(el.getAsJsonObject()));\n" + 
        "        continue;\n" + 
        "      }\n" + 
        "      if (el.isJsonPrimitive()) {\n" + 
        "        arr.add(deserPrimitive(el.getAsJsonPrimitive(), prType));\n" + 
        "      }\n" + 
        "    }\n" + 
        "    \n" + 
        "    return arr;\n" + 
        "  }\n" + 
        "\n" + 
        "  private static HashMap deserMap(JsonObject jo, MjyPrimitiveType prType) {\n" + 
        "    HashMap<String, Object> hm = new HashMap<>();\n" + 
        "    \n" + 
        "    Iterator<Entry<String, JsonElement>> it = jo.entrySet().iterator();\n" + 
        "    \n" + 
        "    while (it.hasNext()) {\n" + 
        "      Entry<String, JsonElement> en = it.next();\n" + 
        "      JsonElement el = en.getValue();\n" + 
        "      if (el.isJsonObject()) {\n" + 
        "        hm.put(en.getKey(), deserObject(el.getAsJsonObject()));\n" + 
        "        continue;\n" + 
        "      }\n" + 
        "      if (el.isJsonPrimitive()) {\n" + 
        "        hm.put(en.getKey(), deserPrimitive(el.getAsJsonPrimitive(), prType));\n" + 
        "      }\n" + 
        "    }\n" + 
        "    \n" + 
        "    return hm;\n" + 
        "  }\n" +
        "\n" +
        "  public static Object deserialize(JsonObject jo) {\n" + 
        "    return deserObject(jo);\n" + 
        "  }\n" + 
        "  \n" + 
        "  public static Object deserialize(String json) {\n" + 
        "    return deserialize(new JsonParser().parse(json).getAsJsonObject());\n" + 
        "  }\n";
  }

}
