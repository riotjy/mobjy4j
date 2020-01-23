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

import java.util.ArrayList;

public class MjyClass {

  private String name;

  private MjyClass generalization;

  private ArrayList<MjyAttribute> attributes = new ArrayList<MjyAttribute>();
  private ArrayList<MjyCollection> collections = new ArrayList<MjyCollection>();

  public MjyClass(String name) {
    super();
    this.name = name;
  }

  public boolean addAttribute(MjyAttribute attribute) {
    attributes.add(attribute);
    return true;
  }

  public int getAttributeCount() {
    return attributes.size();
  }

  public MjyAttribute getAttributeByIndex(int index) {
    return attributes.get(index);
  }

  public boolean addCollection(MjyCollection collection) {
    collections.add(collection);
    return true;
  }

  public int getCollectionCount() {
    return collections.size();
  }

  public MjyCollection getCollectionByIndex(int index) {
    return collections.get(index);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MjyClass getGeneralization() {
    return generalization;
  }

  public void setGeneralization(MjyClass generalization) {
    this.generalization = generalization;
  }
}
