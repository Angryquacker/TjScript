import java.util.ArrayList;

public class CommandLinker {
  public ArrayList<Line> lines = new ArrayList<>();
  
  public void add(String line, int loopStart, int loopEnd) {
    String[] tempArray = line.split(":");

    if(!Utils.commandCheck(tempArray[0])) {
      throw new Error("Invalid Command\nExpected Command: " + Utils.getCommands() + "\n But got " + tempArray[0] + " instead");
    }
    
    if(!Utils.typeCheck(tempArray[1])) {
      throw new Error("Invalid type supplied\nExpected Type: " + Utils.getTypes() + "\n But got " + tempArray[1] + " instead");
    }
  
    String temp = "";
    for (int i = 2;i < tempArray.length;i++) {
      temp += tempArray[i] + ":";
    }

    temp = temp.substring(0, temp.length() - 1);

    this.lines.add(new Line(tempArray[0], tempArray[1], temp, loopStart, loopEnd));
  }

  public Line get(int index) {
    return this.lines.get(index);
  }
}
