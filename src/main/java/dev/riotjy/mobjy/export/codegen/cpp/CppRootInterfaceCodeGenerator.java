package dev.riotjy.mobjy.export.codegen.cpp;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class CppRootInterfaceCodeGenerator extends CodeGenerator {

  public CppRootInterfaceCodeGenerator() {}

  @Override
  public String generate() {
    return 
        "class IMjyRoot {\n" + 
        "public:\n" + 
        "  virtual ~IMjyRoot() {}\n" + 
        "  virtual std::string className() = 0;\n" + 
        "};";
  }

}
