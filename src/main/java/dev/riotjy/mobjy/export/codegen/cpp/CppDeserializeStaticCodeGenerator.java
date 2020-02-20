package dev.riotjy.mobjy.export.codegen.cpp;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class CppDeserializeStaticCodeGenerator extends CodeGenerator {

  public CppDeserializeStaticCodeGenerator() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String generate() {
    // TODO Auto-generated method stub
    return
        "  template <typename T>\n" + 
        "  void deserArray(jsonl::json jo, std::vector<T> & vec) {\n" + 
        "    std::size_t size = jo.size();\n" + 
        "    vec.clear();\n" + 
        "    for (std::size_t i = 0; i < size; ++i) {\n" + 
        "      vec.push_back(jo[i]);\n" + 
        "    }\n" + 
        "  }\n" + 
        "\n" + 
        "  void deserArray(jsonl::json jo, std::vector<shared_ptr<IMjyRoot>> & vec) {\n" + 
        "    std::size_t size = jo.size();\n" + 
        "    vec.clear();\n" + 
        "    for (std::size_t i = 0; i < size; ++i) {\n" + 
        "      vec.push_back(deserObject(jo[i]));\n" + 
        "    }\n" + 
        "  }\n" + 
        "\n" + 
        "  template <typename T>\n" + 
        "  void deserMap(jsonl::json jo, std::map<std::string, T> & map) {\n" + 
        "    map.clear();\n" + 
        "    auto it = jo.cbegin();\n" + 
        "    while (it != jo.cend()) {\n" + 
        "      map.emplace(it.key(), it.value());\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  }\n" + 
        "\n" + 
        "  void deserMap(jsonl::json jo, std::map<std::string, shared_ptr<IMjyRoot>> & map) {\n" + 
        "    map.clear();\n" + 
        "    auto it = jo.cbegin();\n" + 
        "    while (it != jo.cend()) {\n" + 
        "      map.emplace(it.key(), deserObject(jo[it.key()]));\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  }\n" +
        "\n" +
        "  std::shared_ptr<IMjyRoot> deserialize(jsonl::json &  jo) {\n" + 
        "    return deserObject(jo);\n" + 
        "  }\n" + 
        "  \n" + 
        "  std::shared_ptr<IMjyRoot> deserialize(std::string jsonStr) {\n" + 
        "    return deserialize(jsonl::json::parse(jsonStr));\n" + 
        "  }\n" +
        "\n";
  }

}