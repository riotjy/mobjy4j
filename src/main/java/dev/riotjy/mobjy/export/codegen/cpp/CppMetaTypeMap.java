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

import java.util.HashMap;

import dev.riotjy.mobjy.model.MjyPrimitiveType;

public class CppMetaTypeMap {

  public static final CppMetaTypeMap inst = new CppMetaTypeMap();
  
  private HashMap<String, String> map = new HashMap<>();
  
  private CppMetaTypeMap() {
    map.put(MjyPrimitiveType.BOOLEAN.toString(), "bool");
    map.put(MjyPrimitiveType.CHAR.toString(), "char16_t");
    map.put(MjyPrimitiveType.BYTE.toString(), "int8_t");
    map.put(MjyPrimitiveType.SHORT.toString(), "int16_t");
    map.put(MjyPrimitiveType.INT.toString(), "int32_t");
    map.put(MjyPrimitiveType.LONG.toString(), "int64_t");
    map.put(MjyPrimitiveType.FLOAT.toString(), "float");
    map.put(MjyPrimitiveType.DOUBLE.toString(), "double");
    map.put(MjyPrimitiveType.STRING.toString(), "std::string");
  }

  public static CppMetaTypeMap instance() {
    return CppMetaTypeMap.inst;
  }
  
  public String get(String metaType) {
    return map.get(metaType);
  }
}
