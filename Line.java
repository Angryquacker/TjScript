import java.util.ArrayList;

public class Line {
  public String command;
  public String type;
  public String additionalData;
  public int loopStart;
  public int loopEnd;


  public Line(String command, String type, String additionalData, int loopStart, int loopEnd) {
    this.command = command.replaceAll("\\s", "");
    this.type = type.replaceAll("\\s", "");
    this.additionalData = additionalData;
    this.loopStart = loopStart + 1;
    this.loopEnd = loopEnd;
  }
}
