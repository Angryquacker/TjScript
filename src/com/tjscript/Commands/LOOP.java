package com.tjscript.Commands;

import java.io.IOException;
import java.util.ArrayList;

import com.tjscript.Execute;
import com.tjscript.Interpret;
import com.tjscript.Line;
import com.tjscript.Utils;

public class LOOP {
	public static void Loop(Line line) throws IOException {
	    String data = line.additionalData.split(":")[0];
	    int iterations = 50;

	    if(line.type.equals("var")) {
	      iterations = Integer.parseInt(Utils.getVarValue(Execute.variables, data));
	    } else if (line.type.equals("int")) {
	      iterations = Integer.parseInt(data);
	    }

	    iterations--;

	    ArrayList<Line> lines = new ArrayList<>();

	    for (int i = line.loopStart;i < line.loopEnd;i++) {
	      lines.add(Interpret.linker.get(i));
	    }

	    for (int i = 0;i < iterations;i++) {
	      for (int j = 0;j < lines.size();j++) {
	        Execute.run(lines.get(j));
	      }
	    }
	  }
}
