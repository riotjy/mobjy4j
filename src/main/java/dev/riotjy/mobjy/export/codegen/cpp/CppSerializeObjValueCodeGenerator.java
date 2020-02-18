package dev.riotjy.mobjy.export.codegen.cpp;

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class CppSerializeObjValueCodeGenerator extends SerializeValueCodeGenerator {

  public CppSerializeObjValueCodeGenerator() {
  }

  public CppSerializeObjValueCodeGenerator(Collection<String> classNames) {
    super(classNames);
  }

  @Override
  public String generate() {
    String code =
        "private:\n" +
        "  std::string serCValue(IMjyRoot* val) {\n"; 

    for (String className : classNames) {
      code += 
          "    if(val->className() == \"" + className + "\") {\n" + 
          "        return serClass(val->className(), ser" + className + "(dynamic_cast<" + className + ">(val)));\n" +
          "    }\n";
    }
    
    code += 
        "  }\n";
    return code;
  }

}
