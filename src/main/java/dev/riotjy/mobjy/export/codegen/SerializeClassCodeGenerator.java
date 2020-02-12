package dev.riotjy.mobjy.export.codegen;

import java.util.ArrayList;

public abstract class SerializeClassCodeGenerator extends CodeGenerator {

  protected String className;
  protected String generalizationName;

  protected ArrayList<String> attributeNames = new ArrayList<String>();
  protected ArrayList<String> arrayNames = new ArrayList<String>();
  protected ArrayList<String> mapNames = new ArrayList<String>();
  

  public SerializeClassCodeGenerator() {
  }

  public SerializeClassCodeGenerator(String className, String generalizationName) {
    this();
    this.className = className;
    this.generalizationName = generalizationName;
  }
  
  public SerializeClassCodeGenerator(String className, String generalizationName, ArrayList<String> attributeNames,
      ArrayList<String> arrayNames, ArrayList<String> mapNames) {
    this(className, generalizationName);
    this.attributeNames = attributeNames;
    this.arrayNames = arrayNames;
    this.mapNames = mapNames;
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
