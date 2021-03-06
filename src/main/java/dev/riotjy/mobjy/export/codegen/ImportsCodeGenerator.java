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

public abstract class ImportsCodeGenerator extends CodeGenerator {

  protected boolean usesList;
  protected boolean usesMap;

  protected ArrayList<String> requiredImports = new ArrayList<String>();

  public ImportsCodeGenerator() {
    super();
    this.usesList = false;
    this.usesMap = false;
  }

  public ImportsCodeGenerator(boolean usesList, boolean usesMap) {
    this.usesList = usesList;
    this.usesMap = usesMap;
  }

  public boolean isUsesList() {
    return usesList;
  }

  public void setUsesList(boolean usesList) {
    this.usesList = usesList;
  }

  public boolean isUsesMap() {
    return usesMap;
  }

  public void setUsesMap(boolean usesMap) {
    this.usesMap = usesMap;
  }

  public boolean addImport(String requiredImport) {
    if (requiredImports.contains(requiredImport)) {
      return false;
    }
    requiredImports.add(requiredImport);
    return true;
  }

  public int getImportCount() {
    return requiredImports.size();
  }

  public String getImportByIndex(int index) {
    return requiredImports.get(index);
  }
}
