package dev.riotjy.mobjy.export.codegen;

public abstract class ArrayListCodeGenerator extends CodeGenerator {

  protected String name;
  protected String type;
  
  public ArrayListCodeGenerator() {
    super();
  }

  public ArrayListCodeGenerator(String name, String type) {
    super();
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
