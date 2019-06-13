package com.tjscript;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.tjscript.Functions.InterpretFunction;

public class Function {
	public String name;
	public InterpretFunction ex;
	public HashMap<String, String> params;
	public ArrayList<String> paramNames;
	
	public Function(String name, String path) throws IOException {
		this.name = name;
		this.ex = new InterpretFunction(path);
		this.ex.parse();
		
		this.params = this.ex.fData.params;
		this.paramNames = this.ex.fData.paramNames;
	}
	
	public void execute(String[] params) throws IOException {
		this.ex.execute(params);
	}
}
