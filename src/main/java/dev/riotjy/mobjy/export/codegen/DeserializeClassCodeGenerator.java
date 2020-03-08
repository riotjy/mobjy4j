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

import dev.riotjy.mobjy.model.MjyType;

public abstract class DeserializeClassCodeGenerator extends CodeGenerator {

  public DeserializeClassCodeGenerator() {
    super();
  }
  protected String className;
  protected String generalizationName;

  protected HashMap<String, MjyType> attributesInfo = new HashMap<String, MjyType>();
  protected HashMap<String, MjyType> arraysInfo = new HashMap<String, MjyType>();
  protected HashMap<String, MjyType> mapsInfo = new HashMap<String, MjyType>();
  


  public DeserializeClassCodeGenerator(String className, String generalizationName) {
    this();
    this.className = className;
    this.generalizationName = generalizationName;
  }
  
  public DeserializeClassCodeGenerator(String className, String generalizationName, HashMap<String, MjyType> attributesInfo,
      HashMap<String, MjyType> arraysInfo, HashMap<String, MjyType> mapsInfo) {
    this(className, generalizationName);
    this.attributesInfo = attributesInfo;
    this.arraysInfo = arraysInfo;
    this.mapsInfo = mapsInfo;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getGeneralizationName() {
    return generalizationName;
  }

  public void setGeneralizationName(String generalizationName) {
    this.generalizationName = generalizationName;
  }
}
