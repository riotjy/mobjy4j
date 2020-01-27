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

public abstract class ClassCodeGenerator extends CodeGenerator {

  protected String className;
  protected boolean isAbstract;
  protected String generalization;
  
  public ClassCodeGenerator() {
    super();
  }

  public ClassCodeGenerator(String className,
      boolean isAbstract, String generalization) {
    this();
    this.className = className;
    this.isAbstract = isAbstract;
    this.generalization = generalization;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public String getGeneralization() {
    return generalization;
  }

  public void setGeneralization(String generalization) {
    this.generalization = generalization;
  }

}