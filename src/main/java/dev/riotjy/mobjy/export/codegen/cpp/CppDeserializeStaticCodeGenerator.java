/*******************************************************************************
 * Copyright 2020 riotjy and listed authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *    
 *    Authors:
 *      Alex Savulov
 *******************************************************************************/
package dev.riotjy.mobjy.export.codegen.cpp;

import dev.riotjy.mobjy.export.codegen.CodeGenerator;

public class CppDeserializeStaticCodeGenerator extends CodeGenerator {

  public CppDeserializeStaticCodeGenerator() {
  }

  @Override
  public String generate() {
    return
        "  template <typename T>\n" + 
        "  void deserArr(jsonl::json jo, std::vector<T> & vec) {\n" + 
        "    std::size_t size = jo.size();\n" + 
        "    vec.clear();\n" + 
        "    for (std::size_t i = 0; i < size; ++i) {\n" + 
        "      vec.push_back(jo[i]);\n" + 
        "    }\n" + 
        "  }\n" + 
        "\n" + 
        "  template <typename T>\n" + 
        "  void deserArr(jsonl::json jo, std::vector<std::shared_ptr<T>> & vec) {\n" + 
        "    std::size_t size = jo.size();\n" + 
        "    vec.clear();\n" + 
        "    for (std::size_t i = 0; i < size; ++i) {\n" + 
        "      vec.push_back(std::dynamic_pointer_cast<T>(deserObject(jo[i])));\n" + 
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
        "  template <typename T>\n" + 
        "  void deserMap(jsonl::json jo, std::map<std::string, std::shared_ptr<T>> & map) {\n" + 
        "    map.clear();\n" + 
        "    auto it = jo.cbegin();\n" + 
        "    while (it != jo.cend()) {\n" + 
        "      map.emplace(it.key(), std::dynamic_pointer_cast<T>(deserObject(jo[it.key()])));\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  }\n" +
        "\n" +
        "  std::shared_ptr<IMjyRoot> deserialize(jsonl::json &  jo) {\n" + 
        "    return deserObject(jo);\n" + 
        "  }\n" + 
        "  \n" + 
        "  std::shared_ptr<IMjyRoot> deserialize(std::string jsonStr) {\n" + 
        "    jsonl::json jo = jsonl::json::parse(jsonStr);\n" + 
        "    return deserialize(jo);\n" + 
        "  }\n" +
        "\n";
  }

}
