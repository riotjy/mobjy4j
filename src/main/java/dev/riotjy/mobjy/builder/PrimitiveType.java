package dev.riotjy.mobjy.builder;

public enum PrimitiveType {

  INT("int"),
  INT_ARR("int[]"),
  FLOAT("float"),
  FLOAT_ARR("float[]"),
  DOUBLE("double"),
  DOUBLE_ARR("double[]");
  
  private final String type;
  
  PrimitiveType(final String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return this.type;
  }
  
}
