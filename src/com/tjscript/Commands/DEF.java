package com.tjscript.Commands;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Utils;
import com.tjscript.Var;

public class DEF {
	public static void Def(Line line) {
		String[] tempData = line.additionalData.split(":");
		
		if(Utils.checkVarStatus(Execute.variables, tempData[0])) {
			throw new Error("Variable: " + tempData[0] + " already exists");
		}
		
		Execute.variables.add(new Var(tempData[0], line.type));
	}
}
