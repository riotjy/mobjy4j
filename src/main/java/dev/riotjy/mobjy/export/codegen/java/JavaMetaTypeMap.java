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
package dev.riotjy.mobjy.export.codegen.java;

import java.util.HashMap;

import dev.riotjy.mobjy.model.MjyPrimitiveType;

public class JavaMetaTypeMap {

  public static final JavaMetaTypeMap inst = new JavaMetaTypeMap();
  
  private HashMap<String, String> map = new HashMap<>();
  
  private JavaMetaTypeMap() {
    map.put(MjyPrimitiveType.BOOLEAN.toString(), "Boolean");
    map.put(MjyPrimitiveType.CHAR.toString(), "Character");
    map.put(MjyPrimitiveType.BYTE.toString(), "Byte");
    map.put(MjyPrimitiveType.SHORT.toString(), "Short");
    map.put(MjyPrimitiveType.INT.toString(), "Integer");
    map.put(MjyPrimitiveType.LONG.toString(), "Long");
    map.put(MjyPrimitiveType.FLOAT.toString(), "Float");
    map.put(MjyPrimitiveType.DOUBLE.toString(), "Double");
    map.put(MjyPrimitiveType.STRING.toString(), "String");
  }

  public static JavaMetaTypeMap instance() {
    return JavaMetaTypeMap.inst;
  }
  
  public String get(String metaType) {
    return map.get(metaType);
  }
}
