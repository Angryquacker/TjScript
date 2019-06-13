package com.tjscript.Functions;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionData {
	public String returnType;
	public HashMap<String, String> params = new HashMap<>();
	public ArrayList<String> paramNames = new ArrayList<>();

	public FunctionData(String returnType, String[] params) {
		this.returnType = returnType;
		for (int i = 0;i < params.length;i++) {
			String type = params[i].split("-")[0];
			String name = params[i].split("-")[1];
			
			this.params.put(name, type);
			this.paramNames.add(name);
		}
	}
}
