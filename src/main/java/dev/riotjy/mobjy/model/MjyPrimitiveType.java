/*******************************************************************************
 * Copyright 2020 riotjy
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
 *******************************************************************************/
package dev.riotjy.mobjy.model;

public enum MjyPrimitiveType {
  INT,FLOAT,DOUBLE,INVALID;
  
  
  public static MjyPrimitiveType getMjyPrimitiveType(String type) {
    if (type.equals("int")) return INT;
    if (type.equals("float")) return FLOAT;
    if (type.equals("double")) return DOUBLE;
    return INVALID;
  }
  
  @Override
  public String toString() {
    if (this == INT) return "int";
    if (this == FLOAT) return "float";
    if (this == DOUBLE) return "double";
    return "invalid";
  }
}
