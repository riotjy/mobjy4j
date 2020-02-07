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
