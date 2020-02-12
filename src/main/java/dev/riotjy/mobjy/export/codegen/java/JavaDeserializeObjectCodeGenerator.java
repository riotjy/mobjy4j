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
