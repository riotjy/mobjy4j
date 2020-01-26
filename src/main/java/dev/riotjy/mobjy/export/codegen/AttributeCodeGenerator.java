package dev.riotjy.mobjy.export.codegen;

public abstract class AttributeCodeGenerator extends CodeGenerator {

  protected String name;
  protected String type;
  
  public AttributeCodeGenerator() {
    super();
  }

  public AttributeCodeGenerator(String name, String type) {
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
