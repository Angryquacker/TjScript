package com.tjscript.Commands;

import java.io.IOException;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Utils;

public class EXEC {
	public static void Exec(Line line) throws IOException {
		if(!line.type.equals("func")) {
			throw new Error("Expected type func, but got: " + line.type + "instead");
		}
		
		String function = line.additionalData;
		Utils.checkFunctionValidity(Execute.functions, function);
		
		Execute.functions.get(Utils.getFunctionIndex(Execute.functions, function)).ex.execute();
	}
}
