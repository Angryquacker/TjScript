package com.tjscript;   

public class Var {
  public String name;
  public String type;
  public String value;

  public Var(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public void setValue(String value) {
    if(this.type.equals("String")) {
      this.value = value;
    } else {
      this.value = value.replaceAll("\\s", "");
    }
  }
}