package com.tjscript.Commands;

import java.io.IOException;

import com.tjscript.Execute;
import com.tjscript.Line;
import com.tjscript.Utils;

public class EXEC {
	public static void Exec(Line line) throws IOException {
		if(!line.type.equals("function")) {
			throw new Error("Expected type func, but got: " + line.type + " instead");
		}
		
		String[] function = line.additionalData.split(":");
		Utils.checkFunctionValidity(Execute.functions, function[0]);
		
		String[] params = function[1].replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		for (int i = 0;i < params.length;i++) {
			params[i] = params[i].replaceAll("\\s", "");
		}
		
		for (int i = 0;i < Execute.functions.size();i++) {
			if(Execute.functions.get(i).name.equals(function[0])) {
				Execute.functions.get(i).ex.execute(params);
			}
		}
	}
}
