package com.tjscript.Commands;

import java.io.File;
import java.io.IOException;

import com.tjscript.Execute;
import com.tjscript.Function;
import com.tjscript.Line;

public class LOAD {
	public static void Load(Line line) throws IOException {
		if(!line.type.equals("file")) {
			throw new Error("Expected type file, but got: " + line.type + "instead");
		}
		
		String path = line.additionalData;
		String name = new File(path).getName().toString();
		name = name.substring(0, name.length() - 3);
		
		System.out.println(name);
		
		Function func = new Function(name, path);
		
		Execute.functions.add(func);
	}
}
