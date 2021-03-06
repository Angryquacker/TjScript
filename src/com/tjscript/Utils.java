package com.tjscript;

import java.util.ArrayList;
import java.io.File;

public class Utils {
  public static String[] validCommands = {"MOV", "DEF", "OUT", "LOOP", "END", "COMMENT", "LOAD", "EXEC"};
  private static String[] validTypes = {"int", "var", "null", "file", "function"};
  
  public static boolean commandCheck(String command) {
    for (int i = 0;i < validCommands.length;i++) {
      if(command.replaceAll("\\s", "").equals(validCommands[i])) {
        return true;
      }
    }

    return false;
  }

  public static boolean typeCheck(String type) {
    for (int i = 0;i < validTypes.length;i++) {
      if (type.equals(validTypes[i])) {
        return true;
      }
    }

    return false;
  }

  public static String getCommands() {
    String temp = "";
    for (int i = 0;i < validCommands.length;i++) {
      temp += validCommands[i] + ", ";
    }

    return temp;
  }

  public static String getTypes() {
    String temp = "";
    for (int i = 0;i < validTypes.length;i++) {
      temp += validTypes[i] + ", ";
    }

    return temp;
  }

  public static boolean checkVariableValidity(ArrayList<Var> vars, String var) {
    for (int i = 0;i < vars.size();i++) {
      if(var.equals(vars.get(i).name)) {
        return true;
      }
    }
    
    throw new Error("Specified Variable: " + var + " Does not exist");
  }
  
  public static boolean checkVarStatus(ArrayList<Var> vars, String var) {
	    for (int i = 0;i < vars.size();i++) {
	      if(var.equals(vars.get(i).name)) {
	        return true;
	      }
	    }
	    
	    return false;
	  }
  
  public static boolean checkFunctionValidity(ArrayList<Function> funcs, String function) {
	for (int i = 0;i < funcs.size();i++) {
		if(function.equals(funcs.get(i).name)) {
			return true;
		}
	}
	  
	throw new Error("Specified Function: " + function + " Does not exist");
  }

  public static void addCommand(String command) {
    validCommands[validCommands.length - 1] = command;
  }

  public static void addType(String type) {
    validTypes[validTypes.length - 1] = type;
  }
    
  public static String getFileExtension(File file) {
    String extension = "";

    try {
      if (file != null && file.exists()) {
        String name = file.getName();
        extension = name.substring(name.lastIndexOf("."));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return extension;
  }

  public static String getVarValue(ArrayList<Var> vars, String target) {
    for (int i = 0;i < vars.size();i++) {
      if(vars.get(i).name.equals(target)) {
        return vars.get(i).value;
      }
    }

    return "0";
  }
  
  public static int getFunctionIndex(ArrayList<Function> funcs, String function) {
	  for (int i = 0;i < funcs.size();i++) {
		  if(function.equals(funcs.get(i).name)) {
			  return i; 
		  }
	  }
	  
	  
	  return -1;
  }
}