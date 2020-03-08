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

public class MjyElement {

  public MjyElement() {
    // TODO Auto-generated constructor stub
  }

  HashMap<String, MjyLanguageSettings> meta = new HashMap<>();

  public MjyLanguageSettings getMetas(String language) {
    return meta.get(language);
  }
  
  public String getMetaValue(String language, String metaKey) {
    MjyLanguageSettings metas = getMetas(language);
    if (null != metas) {
      return metas.getSetting(metaKey);
    }
    return null;
  }
  
  public void addLanguageMetas(String language) {
    if (meta.containsKey(language))
      return;
    meta.put(language, new MjyLanguageSettings(language));
  }
  
  public void addMetas(String language, String settName, String settValue) {
    addLanguageMetas(language);
    meta.get(language).addSetting(settName, settValue);
  }

  public Set<String> getMetasKeySet() {
    return meta.keySet();
  }


}
