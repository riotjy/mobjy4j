package dev.riotjy.mobjy.export.codegen;

public abstract class ImportsCodeGenerator extends CodeGenerator {

  protected boolean usesList;
  protected boolean usesMap;
  
  public ImportsCodeGenerator() {
    super();
    this.usesList = false;
    this.usesMap = false;
  }

  public ImportsCodeGenerator(boolean usesList, boolean usesMap) {
    this.usesList = usesList;
    this.usesMap = usesMap;
  }

  public boolean isUsesList() {
    return usesList;
  }

  public void setUsesList(boolean usesList) {
    this.usesList = usesList;
  }

  public boolean isUsesMap() {
    return usesMap;
  }

  public void setUsesMap(boolean usesMap) {
    this.usesMap = usesMap;
  }


}
