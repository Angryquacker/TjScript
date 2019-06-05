import java.util.ArrayList;

public class Execute {
  static ArrayList<Var> variables = new ArrayList<>();

  public static int run(Line line) {
    //System.out.println(line.command);

    switch(line.command) {
      case "DEF":
        DEF(line);
        break;
      case "MOV":
        MOV(line);
        break;
      case "OUT":
        OUT(line);
        break;
      case "LOOP":
        LOOP(line);
        break;
    }

    return 0; //Status Codes: 0 -> Ok, -1: Error
  }

  private static void DEF(Line line) {
    String[] tempData = line.additionalData.split(":");
    variables.add(new Var(tempData[0], line.type));
  }

  private static void MOV(Line line) {
    String[] tempData = line.additionalData.split(":");
    String value = tempData[1];
    String target = tempData[0];

    Utils.checkVariableValidity(variables, target);

    switch(line.type) {
      case "int":
        try {
          Integer.parseInt(value);
        } catch(Exception e) {
          e.printStackTrace();
        }
        break;
      case "var":
        Utils.checkVariableValidity(variables, value);

        value = Utils.getVarValue(variables, value);        
        break;
      case "null":
        if (!value.equals("null")) {
          throw new Error("Value should only be null");
        }
        break;
    }

    for (int i = 0;i < variables.size();i++) {
      if (target.equals(variables.get(i).name)) {
        if(!variables.get(i).type.equals(line.type)) {
          throw new Error("Expected type: " + variables.get(i).type + " but got " + line.type + " instead");
        }

        variables.get(i).value = value;
        return;
      }
    }
  }

  private static void OUT(Line line) {
    String[] tempData = line.additionalData.split(":");
    String value = tempData[0];

    if (line.type.equals("var")) {

      Utils.checkVariableValidity(variables, value);

      System.out.println(Utils.getVarValue(variables, value));

    } else {
      if(!line.type.equals("null")) {
        throw new Error("All non-variable outputs should be defined as null. If you were trying to output a variable, use type 'var'");
      }

      System.out.println(value);
    }
  }

  private static void LOOP(Line line) {
    String data = line.additionalData.split(":")[0];
    int iterations = 50;

    if(line.type.equals("var")) {
      iterations = Integer.parseInt(Utils.getVarValue(variables, data));
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
