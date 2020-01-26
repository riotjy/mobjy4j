package dev.riotjy.mobjy.export.codegen;

import java.util.ArrayList;

public abstract class CodeGenerator {

  protected ArrayList<CodeGenerator> parts;
  
  public CodeGenerator() {
  }
  
  public abstract String generate();
  
  public boolean addPart(CodeGenerator part) {
    parts.add(part);
    return true;
  }
}
