package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.AttributeCodeGenerator;

public class JavaAttributeCodeGenerator extends AttributeCodeGenerator {

  public JavaAttributeCodeGenerator() {
    super();
  }

  public JavaAttributeCodeGenerator(String name, String type) {
    super(name, type);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "  public" + type + " " + name + "\n\n";
    // getter
    code += "  public " + type + " get" + capitalized + "() {\n";
    code += "    return this." + name + ";\n";
    code += "  }\n\n";
    // setter
    code += "  public void set" + capitalized + "(" + type + " " + name + ") {\n";
    code += "    this." + name + " = " + name + ";\n";
    code += "  }\n\n";

    return code;
  }

  
}
