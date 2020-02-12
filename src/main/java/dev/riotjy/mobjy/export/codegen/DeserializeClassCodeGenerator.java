package dev.riotjy.mobjy.export.codegen;

import java.util.HashMap;

import dev.riotjy.mobjy.model.MjyType;

public abstract class DeserializeClassCodeGenerator extends CodeGenerator {

  public DeserializeClassCodeGenerator() {
    super();
  }
  protected String className;
  protected String generalizationName;

  protected HashMap<String, MjyType> attributesInfo = new HashMap<String, MjyType>();
  protected HashMap<String, MjyType> arraysInfo = new HashMap<String, MjyType>();
  protected HashMap<String, MjyType> mapsInfo = new HashMap<String, MjyType>();
  


  public DeserializeClassCodeGenerator(String className, String generalizationName) {
    this();
    this.className = className;
    this.generalizationName = generalizationName;
  }
  
  public DeserializeClassCodeGenerator(String className, String generalizationName, HashMap<String, MjyType> attributesInfo,
      HashMap<String, MjyType> arraysInfo, HashMap<String, MjyType> mapsInfo) {
    this(className, generalizationName);
    this.attributesInfo = attributesInfo;
    this.arraysInfo = arraysInfo;
    this.mapsInfo = mapsInfo;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getGeneralizationName() {
    return generalizationName;
  }

  public void setGeneralizationName(String generalizationName) {
    this.generalizationName = generalizationName;
  }
}
