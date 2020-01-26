package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.ImportsCodeGenerator;

public class JavaImportsCodeGenerator extends ImportsCodeGenerator {

  public JavaImportsCodeGenerator() {
    super();
  }

  public JavaImportsCodeGenerator(boolean usesList, boolean usesMap) {
    super(usesList, usesMap);
  }

  @Override
  public String generate() {
    String code = "";
    if (usesList) {
      code += "import java.util.ArrayList;\n";
      code += "import java.util.List;\n";
    }
    if (usesMap) {
      code += "import java.util.HashMap;\n";
      code += "import java.util.Map;\n";
    }
    return code;
  }

}
