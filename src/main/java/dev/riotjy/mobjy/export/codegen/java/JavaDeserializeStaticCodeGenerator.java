package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaDeserializeStaticCodeGenerator extends CodeGenerator {

  public JavaDeserializeStaticCodeGenerator() {}

  @Override
  public String generate() {
    return 
        "  public static Object deserPrimitive(JsonPrimitive jp, MjyPrimitiveType prType) {\n" + 
        "    if (null == prType) {\n" + 
        "      return jp.getAsString();\n" + 
        "    }\n" + 
        "    switch (prType) {\n" + 
        "    case BOOLEAN:\n" + 
        "      return Boolean.valueOf(jp.getAsBoolean());\n" + 
        "    case INT:\n" + 
        "      return Integer.valueOf(jp.getAsInt());\n" + 
        "    case FLOAT:\n" + 
        "      return Float.valueOf(jp.getAsFloat());\n" + 
        "    case DOUBLE:\n" + 
        "      return Double.valueOf(jp.getAsDouble());\n" + 
        "    case STRING:\n" + 
        "      return jp.getAsString();\n" + 
        "    }\n" + 
        "    return null;\n" + 
        "  }\n" + 
        "  \n" + 
        "  public static ArrayList deserArr(JsonArray ja, MjyPrimitiveType prType) {\n" + 
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
        "  \n" + 
        "  public static HashMap deserMap(JsonObject jo, MjyPrimitiveType prType) {\n" + 
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
        "  }";
  }

}
