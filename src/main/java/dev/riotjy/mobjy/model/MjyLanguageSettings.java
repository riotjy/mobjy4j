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

import java.util.HashMap;
import java.util.Set;

public class MjyLanguageSettings {

  private String language;
  
  private HashMap<String, String> settings = new HashMap<>();
  
  private MjyLanguageSettings() {}

  public MjyLanguageSettings(String language) {
    this.language = language;
  }
  
  public String getLanguage() {
    return language;
  }
  
  public void addSetting(String name, String value) {
    settings.put(name, value);
  }
  
  public String getSetting(String name) {
    return settings.get(name);
  }
  
  public Set<String> getSettingNames() {
    return settings.keySet();
  }
}
