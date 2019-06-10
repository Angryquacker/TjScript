package com.tjscript.Commands;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Utils;

public class OUT {
	public static void Out(Line line) {
	    String[] tempData = line.additionalData.split(":");
	    String value = tempData[0];

	    if (line.type.equals("var")) {

	      Utils.checkVariableValidity(Execute.variables, value);

	      System.out.println(Utils.getVarValue(Execute.variables, value));

	    } else {
	      if(!line.type.equals("null")) {
	        throw new Error("All non-variable outputs should be defined as null. If you were trying to output a variable, use type 'var'");
	      }

	      System.out.println(value);
	    }
	  }
}
