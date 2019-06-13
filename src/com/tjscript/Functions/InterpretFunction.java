package com.tjscript.Functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.tjscript.CommandLinker;
import com.tjscript.Execute;
import com.tjscript.Utils;

public class InterpretFunction {
	public String[] commands;
	public String data = "";
	public CommandLinker linker = new CommandLinker();
	public FunctionData fData;
	
	
	public InterpretFunction(String path) throws IOException {
	    File file = new File(path);
	    BufferedReader br = new BufferedReader(new FileReader(file));
	  
	    if(!Utils.getFileExtension(file).equals(".tjf")) {
	      br.close();
	      throw new Error("Invalid file extension, must be .tjf");
	    }

	    String st;
	    while ((st = br.readLine()) != null) {
	      this.data += st;
	    }

	    this.commands = this.data.split(";");

	    br.close();
	}

	public void parse() throws IOException {
		if(!this.commands[0].startsWith("DATA")) {
			throw new Error("First Command of a function file must be \"DATA\"");
		}
		
		String[] functionData = this.commands[0].split(":");
		
		String[] params = functionData[2].split(",");	
		
		for (int i = 0;i < params.length;i++) {
			params[i] = params[i].replaceAll("\\s", "");
		}
		
		params[0] = params[0].replaceAll("\\[", "");
		params[params.length - 1] = params[params.length - 1].replaceAll("\\]", "");
		
		this.commands[0] = "COMMENT:Placeholder value to prevent error";
		
		this.fData = new FunctionData(functionData[0], params);
		
		this.commands = Arrays.copyOf(this.commands, this.commands.length + this.fData.params.size());
		
		for (int j = 0;j < this.fData.paramNames.size();j++) {
			for (int i = this.commands.length - 1;i > j;i--) {
				this.commands[i] = this.commands[i - 1];
			}
		}
		
		for (int i = 0;i < this.fData.params.size();i++) {
			this.commands[i] = "DEF:" + this.fData.params.get(this.fData.paramNames.get(i))  + ":" + this.fData.paramNames.get(i);
		}
		
	    for (int i = 0;i < this.commands.length;i++) {
	      if(this.commands[i].replaceAll("\\s", "").startsWith("LOOP")) {
	        int startIndex = i;
	        int endIndex = 0;

	        for (int j = i;j < this.commands.length;j++) {
	          if (this.commands[j].replaceAll("\\s", "").startsWith("END")) {
	            endIndex = j;
	            linker.add(this.commands[i], startIndex, endIndex);
	            break;
	          }
	        }
	        continue;
	      }

	      linker.add(this.commands[i], 0, 0);
	    }
	    
	    for (int i = 0;i < this.fData.paramNames.size();i++) {
			Execute.run(linker.get(i));
			linker.lines.set(i, linker.get(this.fData.paramNames.size()));
		}
		
	}
	
	public void execute(String[] paramValues) throws IOException {
		if(this.fData.paramNames.size() != paramValues.length) {
			throw new Error("Invalid Number of Paramaters given. Expected: " + this.fData.paramNames.size() +  " but got: " + paramValues.length + " instead");
		}
		
		for (int i = 0;i < this.fData.paramNames.size();i++) {
			String line = "MOV:" + this.fData.params.get(this.fData.paramNames.get(i)) + ":" + this.fData.paramNames.get(i) + ":" + paramValues[i];
			linker.add(line, 0, 0);
		}
		
		for (int i = this.linker.lines.size() - 1;i > this.linker.lines.size() - this.fData.paramNames.size() - 1;i--) {
			Execute.run(linker.get(i));
			linker.lines.set(i, linker.get(this.fData.paramNames.size()));
		}
		
		for (int i = 0;i < this.commands.length;i++) {
			Execute.run(linker.get(i));
		}
	}
}
