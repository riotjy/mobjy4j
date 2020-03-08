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

import java.util.ArrayList;

public abstract class SerializeClassCodeGenerator extends CodeGenerator {

  protected String className;
  protected String generalizationName;

  public SerializeClassCodeGenerator() {
  }

  public SerializeClassCodeGenerator(String className, String generalizationName) {
    this();
    this.className = className;
    this.generalizationName = generalizationName;
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
