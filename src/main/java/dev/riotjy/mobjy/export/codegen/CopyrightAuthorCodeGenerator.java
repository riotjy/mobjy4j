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

public abstract class CopyrightAuthorCodeGenerator extends CodeGenerator {

  protected String copyright;
  protected String author;
  
  public CopyrightAuthorCodeGenerator() {
    super();
  }

  public CopyrightAuthorCodeGenerator(String copyright, String author) {
    super();
    this.copyright = copyright;
    this.author = author;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
