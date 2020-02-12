package dev.riotjy.mobjy.export.codegen.java;

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class JavaSerializeValueCodeGenerator extends SerializeValueCodeGenerator {

  public JavaSerializeValueCodeGenerator() {
    super();
  }

  public JavaSerializeValueCodeGenerator(Collection<String> fieldNames) {
    super(fieldNames);
  }

  @Override
  public String generate() {
    String code = 
        "  private static String serValue(Object value) {\n" + 
        "    \n" + 
        "    if (null == value)\n" + 
        "      return \"null\";\n" + 
        "    \n" + 
        "    if (value instanceof Boolean ||\n" + 
        "        value instanceof Integer ||\n" + 
        "        value instanceof Float ||\n" + 
        "        value instanceof Double) {\n" + 
        "      return value.toString();\n" + 
        "    }\n" + 
        "    \n" + 
        "    if (value instanceof String) {\n" + 
        "      return \"\\\"\" + ((String)value).toString() + \"\\\"\";\n" + 
        "    }\n" + 
        "\n" + 
        "    String className = value.getClass().getSimpleName();\n" + 
        "    switch(className) {\n";
    for (String className : classNames) {
      code += 
          "    case \"" + className + "\":\n" + 
          "      return serClass(className,ser" + className + "((" + className + ")value));\n";
    }
    
    code += 
        "    default:\n" + 
        "      return \"\";\n" + 
        "    }\n" + 
        "  }\n";
    return code;
  }
  

}
