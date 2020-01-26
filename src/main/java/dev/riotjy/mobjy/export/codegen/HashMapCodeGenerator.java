package dev.riotjy.mobjy.export.codegen;

public abstract class HashMapCodeGenerator extends CodeGenerator {

  protected String name;
  protected String valueType;
  
  public HashMapCodeGenerator() {
    super();
  }

  public HashMapCodeGenerator(String name, String valueType) {
    super();
    this.name = name;
    this.valueType = valueType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

}
