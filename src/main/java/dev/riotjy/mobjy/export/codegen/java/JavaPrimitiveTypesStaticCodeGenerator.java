package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaPrimitiveTypesStaticCodeGenerator extends CodeGenerator {

  public JavaPrimitiveTypesStaticCodeGenerator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String generate() {
    return 
        "public enum MjyPrimitiveType {\n" + 
        "  BOOLEAN,\n" + 
        "  INT,\n" + 
        "  FLOAT,\n" + 
        "  DOUBLE,\n" + 
        "  STRING,\n" + 
        "  INVALID;\n" + 
        "}";
  }

}
