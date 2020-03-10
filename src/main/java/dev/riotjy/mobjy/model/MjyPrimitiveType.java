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
package dev.riotjy.mobjy.model;

public enum MjyPrimitiveType {
  BOOLEAN,
  CHAR,
  BYTE,
  SHORT,
  INT,
  LONG,
  FLOAT,
  DOUBLE,
  STRING,
  INVALID;
  
  
  public static MjyPrimitiveType getMjyPrimitiveType(String type) {
    if (type.equals("bool")) return BOOLEAN;
    if (type.equals("char")) return CHAR;
    if (type.equals("byte")) return BYTE;
    if (type.equals("short")) return SHORT;
    if (type.equals("int")) return INT;
    if (type.equals("long")) return LONG;
    if (type.equals("float")) return FLOAT;
    if (type.equals("double")) return DOUBLE;
    if (type.equals("string")) return STRING;
    return INVALID;
  }
  
  @Override
  public String toString() {
    if (this == BOOLEAN) return "bool";
    if (this == CHAR) return "char";
    if (this == BYTE) return "byte";
    if (this == SHORT) return "short";
    if (this == INT) return "int";
    if (this == LONG) return "long";
    if (this == FLOAT) return "float";
    if (this == DOUBLE) return "double";
    if (this == STRING) return "string";
    return "invalid";
  }
}
