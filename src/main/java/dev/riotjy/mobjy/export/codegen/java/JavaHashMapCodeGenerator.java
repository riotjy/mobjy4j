package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.ArrayListCodeGenerator;

public class JavaHashMapCodeGenerator extends ArrayListCodeGenerator {

  public JavaHashMapCodeGenerator() {
    super();
  }

  public JavaHashMapCodeGenerator(String name, String valueType) {
    super(name, valueType);
  }

  @Override
  public String generate() {
    String capitalized = name.substring(0,1).toUpperCase() + name.substring(1);
    String code = "  public List<" + type + "> " + name + " = new ArrayList<>();\n\n";
    // size
    code += "  public int sizeOf" + capitalized + "() {\n";
    code += "    return this." + name + ".size();\n";
    code += "  }\n\n";
    // pop front
    code += "  public " + type + " getFrom" + capitalized + "(String key) {\n";
    code += "    return this." + name + ".get(key);\n";
    code += "  }\n\n";
    // push back
    code += "  public void addTo" + capitalized + "(String key, " + type + " " + name + ") {\n";
    code += "    this." + name + ".put(" + name + ");\n";
    code += "  }\n\n";
    // iterator
    code += "  public Iterator iteratorOf" + capitalized + "() {\n";
    code += "    return this." + name + ".iterator();\n";
    code += "  }\n";
    return code;
  }

}
