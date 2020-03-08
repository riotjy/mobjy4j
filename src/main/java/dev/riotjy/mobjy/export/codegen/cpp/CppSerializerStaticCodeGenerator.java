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

public class CppSerializerStaticCodeGenerator extends CodeGenerator {

  public CppSerializerStaticCodeGenerator() {
  }

  @Override
  public String generate() {
    return
        "private:\n"+
        "  std::string qtd(std::string in) {\n" + 
        "    return \"\\\"\" + in + \"\\\"\";\n" + 
        "  }\n" + 
        "  std::string con(std::string first, std::string second) {\n" + 
        "    return first + \":\" + second;\n" + 
        "  }\n" + 
        "  std::string lin(std::string in) {\n" + 
        "    return in + \",\";\n" + 
        "  }\n" + 
        "  std::string crl(std::string in) {\n" + 
        "    return \"{\" + in + \"}\";\n" + 
        "  }\n" + 
        "  \n" + 
        "  std::string serClass(std::string className, std::string fields) {\n" + 
        "    std::string json =  \"{\\\"cnid\\\":\\\"\" + className + \"\\\"\";\n" + 
        "    if (!fields.empty()) {\n" + 
        "      json += \",\" + fields;\n" + 
        "    }\n" + 
        "    json += \"}\";\n" + 
        "    return json;\n" + 
        "  }\n" +
        "  \n" + 
        "  std::string serBool(bool val) {\n" + 
        "    return val ? \"true\" : \"false\";\n" + 
        "  }\n" + 
        "\n" + 
        "  template <typename T>\n" + 
        "  std::string serArr(std::vector<std::shared_ptr<T>> arr) {\n" + 
        "    std::string json = \"[\";\n" + 
        "    typename std::vector<std::shared_ptr<T>>::const_iterator it = arr.cbegin();\n" + 
        "  \n" + 
        "    while (it != arr.cend()) {\n" + 
        "      if (it != --arr.cend()) {\n" + 
        "        json += lin(serCValue(it->get()));\n" + 
        "      } else {\n" + 
        "        json += serCValue(it->get());\n" + 
        "      }\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"]\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "  \n" + 
        "  template <typename T>\n" + 
        "  std::string serArr(std::vector<T> arr) {\n" + 
        "  \n" + 
        "    int arrSize = arr.size();\n" + 
        "    std::string json = \"[\";\n" + 
        "  \n" + 
        "    for (int i = 0; i < arrSize; ++i) {\n" + 
        "      T val = arr[i];\n" + 
        "      if (i < arrSize - 1) {\n" + 
        "        json += lin(std::to_string(val));\n" + 
        "      } else {\n" + 
        "        json += std::to_string(val);\n" + 
        "      }\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"]\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "\n" + 
        "  std::string serArr(std::vector<bool> arr) {\n" + 
        "\n" + 
        "    int arrSize = arr.size();\n" + 
        "    std::string json = \"[\";\n" + 
        "\n" + 
        "    for (int i = 0; i < arrSize; ++i) {\n" + 
        "      bool val = arr[i];\n" + 
        "      if (i < arrSize - 1) {\n" + 
        "        json += lin(serBool(val));\n" + 
        "      } else {\n" + 
        "        json += serBool(val);\n" + 
        "      }\n" + 
        "    }\n" + 
        "\n" + 
        "    json += \"]\";\n" + 
        "    return json;\n" + 
        "  }\n" +
        "  \n" + 
        "  std::string serArr(std::vector<std::string> arr) {\n" + 
        "  \n" + 
        "    int arrSize = arr.size();\n" + 
        "    std::string json = \"[\";\n" + 
        "  \n" + 
        "    for (int i = 0; i < arrSize; ++i) {\n" + 
        "      std::string val = arr[i];\n" + 
        "      if (i < arrSize - 1) {\n" + 
        "        json += lin(qtd(val));\n" + 
        "      } else {\n" + 
        "        json += qtd(val);\n" + 
        "      }\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"]\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "  \n" + 
        "  template <typename T>\n" + 
        "  std::string serMap(std::map<std::string, std::shared_ptr<T>> hm) {\n" + 
        "    std::string json = \"{\";\n" + 
        "  \n" + 
        "    typename std::map<std::string, std::shared_ptr<T>>::const_iterator it = hm.cbegin();\n" + 
        "  \n" + 
        "    while (it != hm.cend()) {\n" + 
        "      if (it != --hm.cend()) {\n" + 
        "        json += lin(con(qtd(it->first), serCValue(it->second.get())));\n" + 
        "      } else {\n" + 
        "        json += con(qtd(it->first), serCValue(it->second.get()));\n" + 
        "      }\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"}\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "  \n" + 
        "  \n" + 
        "  template <typename T>\n" + 
        "  std::string serMap(std::map<std::string, T> map) {\n" + 
        "    std::string json = \"{\";\n" + 
        "  \n" + 
        "    typename std::map<std::string, T>::const_iterator it = map.cbegin();\n" + 
        "  \n" + 
        "    while (it != map.cend()) {\n" + 
        "      if (it != --map.cend()) {\n" + 
        "        json += lin(con(qtd(it->first), std::to_string(it->second)));\n" + 
        "      } else {\n" + 
        "        json += con(qtd(it->first), std::to_string(it->second));\n" + 
        "      }\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"}\";\n" + 
        "    return json;\n" + 
        "  }\n" + 
        "  \n" + 
        "  std::string serMap(std::map<std::string, bool> map) {\n" + 
        "    std::string json = \"{\";\n" + 
        "\n" + 
        "    std::map<std::string, bool>::const_iterator it = map.cbegin();\n" + 
        "\n" + 
        "    while (it != map.cend()) {\n" + 
        "      if (it != --map.cend()) {\n" + 
        "        json += lin(con(qtd(it->first), serBool(it->second)));\n" + 
        "      } else {\n" + 
        "        json += con(qtd(it->first), serBool(it->second));\n" + 
        "      }\n" + 
        "      ++it;\n" + 
        "    }\n" +
        "  }\n" + 
        "\n"+
        "  std::string serMap(std::map<std::string, std::string> map) {\n" + 
        "    std::string json = \"{\";\n" + 
        "  \n" + 
        "    std::map<std::string, std::string>::const_iterator it = map.cbegin();\n" + 
        "  \n" + 
        "    while (it != map.cend()) {\n" + 
        "      if (it != --map.cend()) {\n" + 
        "        json += lin(con(qtd(it->first), qtd(it->second)));\n" + 
        "      } else {\n" + 
        "        json += con(qtd(it->first), qtd(it->second));\n" + 
        "      }\n" + 
        "      ++it;\n" + 
        "    }\n" + 
        "  \n" + 
        "    json += \"}\";\n" + 
        "    return json;\n" + 
        "  }\n" +
        "public:\n" +
        "  std::string serialize(IMjyRoot& val) {\n" +
        "    return serCValue(&val);\n" +
        "  }\n";
  }

}
