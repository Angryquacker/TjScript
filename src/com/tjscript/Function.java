package com.tjscript;

import java.io.IOException;

public class Function {
	public String name;
	public Interpret ex;
	
	public Function(String name, String path) throws IOException {
		this.name = name;
		this.ex = new Interpret(path);
		this.ex.parse();
	}
}
