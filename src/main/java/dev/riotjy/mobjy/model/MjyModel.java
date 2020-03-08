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
import java.util.Spliterator;

public class MjyModel {

  private String project;
  
  private MjyVersion version;
  private MjyVersion compatVersion;
  
  private HashMap<String, MjyLanguageSettings> langSettings = new HashMap<>();
  
  private ArrayList<MjyClass> classes = new ArrayList<MjyClass>();
  
  MjyModel() {}
  
  public boolean addClass(MjyClass clazz) {
    classes.add(clazz);
    return true;
  }
  
  public int getClassCount() {
    return classes.size();
  }
  
  public MjyClass getClassByIndex(int index) {
    return classes.get(index);
  }
  
  public MjyClass getClassByName(String name) {
    for (MjyClass mjyClass : classes) {
      if (mjyClass.getName().equals(name)) {
        return mjyClass;
      }
    }
    return null;
  }
  
  public Spliterator<MjyClass> getClassSpliterator( ) {
    return classes.spliterator();
  }
  
  public Iterator<MjyClass> getClassIterator() {
    return classes.iterator();
  }
  
  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public MjyVersion getVersion() {
    return version;
  }

  public void setVersion(MjyVersion version) {
    this.version = version;
  }

  public MjyVersion getCompatVersion() {
    return compatVersion;
  }

  public void setCompatVersion(MjyVersion compatVersion) {
    this.compatVersion = compatVersion;
  }

  public ArrayList<MjyClass> getClasses() {
    return classes;
  }

  public void setClasses(ArrayList<MjyClass> classes) {
    this.classes = classes;
  }

  public MjyLanguageSettings getLanguageSettings(String language) {
    return langSettings.get(language);
  }
  
  public String getLanguageSettingValue(String language, String settName) {
    MjyLanguageSettings langSett = getLanguageSettings(language);
    if (null != langSett) {
      return langSett.getSetting(settName);
    }
    return null;
  }
  
  public void addLanguage(String language) {
    if (langSettings.containsKey(language))
      return;
    langSettings.put(language, new MjyLanguageSettings(language));
  }
  
  public void addLanguageSetting(String language, String settName, String settValue) {
    addLanguage(language);
    langSettings.get(language).addSetting(settName, settValue);
  }

  public Set<String> getLanguageKeySet() {
    return langSettings.keySet();
  }
}
