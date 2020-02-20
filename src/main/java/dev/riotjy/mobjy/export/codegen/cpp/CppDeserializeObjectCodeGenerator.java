package dev.riotjy.mobjy.export.codegen.cpp;

import java.util.Collection;

import dev.riotjy.mobjy.export.codegen.SerializeValueCodeGenerator;

public class CppDeserializeObjectCodeGenerator extends SerializeValueCodeGenerator {

  public CppDeserializeObjectCodeGenerator() {
    super();
  }

  public CppDeserializeObjectCodeGenerator(Collection<String> classNames) {
    super(classNames);
  }

  @Override
  public String generate() {
    String code = 
        "  std::shared_ptr<IMjyRoot> deserObject(jsonl::json &  jo) {\n" + 
        "    std::string cnid = jo[\"cnid\"];\n" + 
        "\n";

    for (String className : classNames) {
      code += 
          "    if (cnid == \"" + className + "\") {\n" + 
          "      std::shared_ptr<" + className + "> obj = std::make_shared<" + className + ">();\n" + 
          "      deser" + className + "(jo, obj.get());\n" + 
          "      return obj;\n" + 
          "    }\n";
    }

    code += 
        "    return nullptr;\n" + 
        "  }\n" + 
        "\n";
    return code;
  }

}
