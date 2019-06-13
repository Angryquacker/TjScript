package com.tjscript.Commands;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Utils;

public class MOV {
	public static void Mov(Line line) {
	    String[] tempData = line.additionalData.split(":");
	    String value = tempData[1];
	    String target = tempData[0];

	    Utils.checkVariableValidity(Execute.variables, target);
	    
	    switch(line.type) {
	      case "int":
	        try {
	          Integer.parseInt(value);
	        } catch(Exception e) {
	          e.printStackTrace();
	        }
	        break;
	      case "var":
	        Utils.checkVariableValidity(Execute.variables, value);

	        value = Utils.getVarValue(Execute.variables, value);        
	        break;
	      case "null":
	        if (!value.equals("null")) {
	          throw new Error("Value should only be null");
	        }
	        break;
	    }

	    for (int i = 0;i < Execute.variables.size();i++) {
	      if (target.equals(Execute.variables.get(i).name)) {
	        if(!Execute.variables.get(i).type.equals(line.type)) {
	          throw new Error("Expected type: " + Execute.variables.get(i).type + " but got " + line.type + " instead");
	        }

	        Execute.variables.get(i).value = value;
	        return;
	      }
	    }
	  }
}
