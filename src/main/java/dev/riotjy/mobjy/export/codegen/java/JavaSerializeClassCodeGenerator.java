package dev.riotjy.mobjy.export.codegen.java;

import java.util.ArrayList;

import dev.riotjy.mobjy.export.codegen.SerializeClassCodeGenerator;

public class JavaSerializeClassCodeGenerator extends SerializeClassCodeGenerator {

  public JavaSerializeClassCodeGenerator() {
  }

  public JavaSerializeClassCodeGenerator(String className, String generalizationName) {
    super(className, generalizationName);
  }

  public JavaSerializeClassCodeGenerator(String className, String generalizationName, ArrayList<String> fieldNames) {
    super(className, generalizationName, fieldNames);
  }

  @Override
  public String generate() {
    
    boolean hasFields = !fieldNames.isEmpty();
    String code = 
        "  public static String ser" + className + "(" + className + " value) {\n" +
        "    return\n";
    
    if (null != generalizationName) {
      code += "        ";
      if (hasFields) {
        code += "lin(";
      }
      code += "ser" + generalizationName + "(" + generalizationName + " value)"; 
      if (hasFields) {
        code += ") +\n";
      }
    } else {
      if (!hasFields) {
        code += 
            "        // TODO: REPLACE WITH SERIALIZATION CODE FOR CLASS " + className + "\n" +
            "        \"\"";
      }
    }
    
    for (int i = 0; i < fieldNames.size(); ++i) {
      code += "        ";
      boolean notLast = (i + 1) < fieldNames.size();
      if (notLast) {
        code += "lin(";
      }
      code += "con(qtd(" + fieldNames.get(i) + "), serValue(value." + fieldNames.get(i) + "))";
      if (notLast) {
        code += ") +\n";
      }
    }

    code += ";\n  }\n";
    return code;
  }

}
