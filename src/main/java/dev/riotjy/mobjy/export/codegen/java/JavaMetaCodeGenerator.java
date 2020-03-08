package dev.riotjy.mobjy.export.codegen.java;

import dev.riotjy.mobjy.export.codegen.MetaCodeGenerator;

public class JavaMetaCodeGenerator extends MetaCodeGenerator {

  public JavaMetaCodeGenerator(int indent) {
    super(indent);
  }

  @Override
  public String generate() {

    String code = "";
    String indent = indent(this.indent);
    
    for (String key : this.metas.keySet()) {
      String value = metas.get(key);
      if (null == value) value = "";
      code += indent + "@" + key + value + "\n";
    }
    
    return code;
  }

}
