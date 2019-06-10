package com.tjscript;

import java.io.IOException;
import java.util.ArrayList;

import com.tjscript.Commands.DEF;
import com.tjscript.Commands.EXEC;
import com.tjscript.Commands.LOAD;
import com.tjscript.Commands.LOOP;
import com.tjscript.Commands.MOV;
import com.tjscript.Commands.OUT;

public class Execute {
  public static ArrayList<Var> variables = new ArrayList<>();
  public static ArrayList<Function> functions = new ArrayList<>();

  public static int run(Line line) throws IOException {
    System.out.println(line.command);

    switch(line.command) {
      case "DEF":
        DEF.Def(line);
        break;
      case "MOV":
        MOV.Mov(line);
        break;
      case "OUT":
        OUT.Out(line);
        break;
      case "LOOP":
        LOOP.Loop(line);
        break;
      case "LOAD":
    	LOAD.Load(line);
    	break;
      case "EXEC":
    	EXEC.Exec(line);
    	break;
    }

    return 0; //Status Codes: 0 -> Ok, -1: Error
  }
}