package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaSerializeStaticCodeGenerator extends CodeGenerator {

  public JavaSerializeStaticCodeGenerator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String generate() {
    return 
        "  private static String serClass(String className, String fields) {\n" + 
        "    return \"{\\\"cnid\\\":\\\"\" + className + \"\\\",\" + fields + \"}\";\n" + 
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
        "  }\n";
  }

}
