package dev.riotjy.mobjy.util;

public enum StrConst {

  CLI_IN_FILE_NAME("i"),
  CLI_OUT_PATH("o"),
  CLI_PACKAGE_NAME("p"),
  CLI_HELP("h"),
    
  EMPTY_STR("");
  
  private String string;
  
  StrConst(String str) {this.string = str;}
  public String toString() {return this.string;}
}
