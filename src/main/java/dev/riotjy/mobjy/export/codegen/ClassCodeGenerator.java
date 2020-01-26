package dev.riotjy.mobjy.export.codegen;

public abstract class ClassCodeGenerator extends CodeGenerator {

  protected String className;
  protected boolean isAbstract;
  protected String generalization;
  
  public ClassCodeGenerator() {
    super();
  }

  public ClassCodeGenerator(String className,
      boolean isAbstract, String generalization) {
    this();
    this.className = className;
    this.isAbstract = isAbstract;
    this.generalization = generalization;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public boolean isAbstract() {
    return isAbstract;
  }

  public void setAbstract(boolean isAbstract) {
    this.isAbstract = isAbstract;
  }

  public String getGeneralization() {
    return generalization;
  }

  public void setGeneralization(String generalization) {
    this.generalization = generalization;
  }

}
