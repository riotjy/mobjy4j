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
package dev.riotjy.mobjy.export.codegen;

import java.util.ArrayList;

public abstract class SerializeClassCodeGenerator extends CodeGenerator {

  protected String className;
  protected String generalizationName;

  protected ArrayList<String> attributeNames = new ArrayList<String>();
  protected ArrayList<String> arrayNames = new ArrayList<String>();
  protected ArrayList<String> mapNames = new ArrayList<String>();
  

  public SerializeClassCodeGenerator() {
  }

  public SerializeClassCodeGenerator(String className, String generalizationName) {
    this();
    this.className = className;
    this.generalizationName = generalizationName;
  }
  
  public SerializeClassCodeGenerator(String className, String generalizationName, ArrayList<String> attributeNames,
      ArrayList<String> arrayNames, ArrayList<String> mapNames) {
    this(className, generalizationName);
    this.attributeNames = attributeNames;
    this.arrayNames = arrayNames;
    this.mapNames = mapNames;
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
