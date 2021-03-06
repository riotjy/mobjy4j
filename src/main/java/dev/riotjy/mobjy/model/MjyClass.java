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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MjyClass extends MjyElement {

  private String name;
  
  private boolean usesArrayList;
  private boolean usesMap;

  private MjyClass generalization;

  private boolean isExternal = false;
  
  private ArrayList<MjyAttribute> attributes = new ArrayList<MjyAttribute>();

  private ArrayList<MjyCollection> collections = new ArrayList<MjyCollection>();
  
  private HashMap<String, String> langDepClasses = new HashMap<>();
  private HashMap<String, String> langDepResources = new HashMap<>();
  
  private HashMap<String, ArrayList<String>> langRequiredImports = new HashMap<>();

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
  
  public ArrayList<String> getAttributeNames() {
    ArrayList<String> names = new ArrayList<String>();
    attributes.forEach(v -> names.add(v.getName()));
    return names;
  }

  public HashMap<String, MjyType> getAttributesInfo() {
    HashMap<String, MjyType> info = new HashMap<String, MjyType>();
    attributes.forEach(v -> info.put(v.getName(), v.getType()));
    return info;
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

  public ArrayList<String> getCollectionNames() {
    ArrayList<String> names = new ArrayList<String>();
    collections.forEach(v -> names.add(v.getName()));
    return names;
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

  public boolean usesArrayList() {
    return usesArrayList;
  }

  public void setUsesArrayList(boolean usesArrayList) {
    this.usesArrayList = usesArrayList;
  }

  public boolean usesMap() {
    return usesMap;
  }

  public void setUsesMap(boolean usesMap) {
    this.usesMap = usesMap;
  }

  public boolean isExternal() {
    return isExternal;
  }

  public void setExternal(boolean isExternal) {
    this.isExternal = isExternal;
  }

  public void addLangDepClass(String lang, String className) {
    langDepClasses.put(lang, className);
  }
  
  public String getLangDepClass(String lang) {
    return langDepClasses.get(lang);
  }
  
  public Set<String> getLangKeySet() {
    return langDepClasses.keySet();
  }
  
  public void addLangDepResource(String lang, String resName) {
    langDepResources.put(lang, resName);
  }
  
  public String getLangDepResource(String lang) {
    return langDepResources.get(lang);
  }
  
  public void addLangImport(String lang, String imps) {
    ArrayList<String> langList = langRequiredImports.get(lang);
    if (null == langList) {
      langList = new ArrayList<>();
      langRequiredImports.put(lang, langList);
    }
    langList.add(imps);
  }
  
  public Iterator<String> getLangImportsIter(String lang) {
    ArrayList<String> langList = langRequiredImports.get(lang);
    if (null == langList) {
      return null;
    }
    return langList.iterator();
  }
  
  public ArrayList<String> getFieldNames() {
    ArrayList<String> ret = getAttributeNames();
    collections.forEach(v -> ret.add(v.getName()));
    return ret;
  }
  
  public HashMap<String, MjyType> getFieldsInfo() {
    HashMap<String, MjyType> info = getAttributesInfo();
    collections.forEach(v -> info.put(v.getName(), v.getValueType()));
    return info;
  }

  public ArrayList<String> getArrayNames() {
    ArrayList<String> names = new ArrayList<String>();
    collections.forEach(v -> {
      if (v.getCollectionType() == MjyCollectionType.ARRAYLIST)
        names.add(v.getName());
    });
    return names;
  }

  public HashMap<String, MjyType> getArraysInfo() {
    HashMap<String, MjyType> info = new HashMap<String, MjyType>();
    collections.forEach(v -> {
      if (v.getCollectionType() == MjyCollectionType.ARRAYLIST)
        info.put(v.getName(), v.getValueType());
    });;
    return info;
  }

  public ArrayList<String> getMapNames() {
    ArrayList<String> names = new ArrayList<String>();
    collections.forEach(v -> {
      if (v.getCollectionType() == MjyCollectionType.HASHMAP)
        names.add(v.getName());
    });
    return names;
  }

  public HashMap<String, MjyType> getMapsInfo() {
    HashMap<String, MjyType> info = new HashMap<String, MjyType>();
    collections.forEach(v -> {
      if (v.getCollectionType() == MjyCollectionType.HASHMAP)
        info.put(v.getName(), v.getValueType());
    });;
    return info;
  }
}
