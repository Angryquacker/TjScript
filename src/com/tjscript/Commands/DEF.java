package com.tjscript.Commands;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Var;

public class DEF {
	public static void Def(Line line) {
		String[] tempData = line.additionalData.split(":");
	    Execute.variables.add(new Var(tempData[0], line.type));
	}
}
