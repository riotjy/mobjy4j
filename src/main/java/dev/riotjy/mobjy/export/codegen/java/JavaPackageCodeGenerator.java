package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.PackageCodeGenerator;

public class JavaPackageCodeGenerator extends PackageCodeGenerator {

  public JavaPackageCodeGenerator() {
    super();
  }

  public JavaPackageCodeGenerator(String packageName) {
    super(packageName);
  }

  @Override
  public String generate() {
    return "package " + packageName + ";\n";
  }

}
