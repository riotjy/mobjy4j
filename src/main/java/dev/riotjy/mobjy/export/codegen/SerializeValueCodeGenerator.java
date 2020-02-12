package dev.riotjy.mobjy.export.codegen;

import java.util.ArrayList;
import java.util.Collection;

public abstract class SerializeValueCodeGenerator extends CodeGenerator {

  protected ArrayList<String> classNames = new ArrayList<String>();
  
  public SerializeValueCodeGenerator() {
  }

  public SerializeValueCodeGenerator(Collection<String> classNames) {
    this();
    this.classNames.addAll(classNames);
  }

  public void addClassName(String fieldName) {
    classNames.add(fieldName);
  }

}
