package dev.riotjy.mobjy.export.codegen;

public abstract class PackageCodeGenerator extends CodeGenerator {

  protected String packageName;
  
  public PackageCodeGenerator() {
    super();
  }

  public PackageCodeGenerator(String packageName) {
    super();
    this.packageName = packageName;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

}
