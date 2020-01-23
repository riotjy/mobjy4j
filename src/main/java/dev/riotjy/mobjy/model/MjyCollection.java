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

public class MjyCollection {

  private MjyType valueType;
  private String name;
  private MjyCollectionType collectionType;

  MjyCollection(String name, MjyCollectionType collectionType, MjyType valueType) {
    this.valueType = valueType;
    this.name = name;
    this.collectionType = collectionType;
  }

  public MjyType getValueType() {
    return valueType;
  }

  public void setValueType(MjyType valueType) {
    this.valueType = valueType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MjyCollectionType getCollectionType() {
    return collectionType;
  }

  public void setCollectionType(MjyCollectionType collectionType) {
    this.collectionType = collectionType;
  }
}