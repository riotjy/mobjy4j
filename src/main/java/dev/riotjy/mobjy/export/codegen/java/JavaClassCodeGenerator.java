package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.ClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class JavaClassCodeGenerator extends ClassCodeGenerator {

  public JavaClassCodeGenerator() {
    super();
  }

  public JavaClassCodeGenerator(String className, boolean isAbstract, String generalization) {
    super(className, isAbstract, generalization);
  }

  @Override
  public String generate() {
    String code = "public " +
        (isAbstract ? "abstract " : " ") + "class " +
        className +
        (null == generalization ? "" : " extends " + generalization) +
        " {\n";

    for (CodeGenerator part : parts) {
      code += part.generate() + "\n";
    }

    code += "}\n";
    return code;
  }

}
