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
package dev.riotjy.mobjy.export.codegen;

import java.util.HashMap;

public abstract class MetaCodeGenerator extends CodeGenerator {

  protected int indent = 0;
  protected HashMap<String, String> metas = new HashMap<>();
  
  public MetaCodeGenerator() {
    super();
  }
  
  public MetaCodeGenerator(int indent) {
    this();
    this.indent = indent;
  }

  public boolean putMeta(String key, String value) {
    return metas.put(key, value) != null;
  }
  
  protected static String indent(int indent) {
    StringBuilder sb = new StringBuilder();
    while (indent > 0) {
      sb.append(" ");
      --indent;
    }
    return sb.toString();
  }
  
//  public String getMeta(String key) {
//    return metas.get(key);
//  }
}
