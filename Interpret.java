import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Interpret {
  private String[] commands;
  private String data = "";
  public static CommandLinker linker = new CommandLinker();

  public Interpret(String path) throws IOException {
    File file = new File(path);
    BufferedReader br = new BufferedReader(new FileReader(file));
  
    if(!Utils.getFileExtension(file).equals(".tj")) {
      br.close();
      throw new Error("Invalid file extension, must be .tj");
    }

    String st;
    while ((st = br.readLine()) != null) {
      this.data += st;
    }

    this.commands = this.data.split(";");

    br.close();
  }

  public void parse() {
    for (int i = 0;i < this.commands.length;i++) {
      if(this.commands[i].replaceAll("\\s","").startsWith("//")) continue;
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
  }

  public void execute() {
    for (int i = 0;i < linker.lines.size();i++) {
      Execute.run(linker.get(i));
    }
  }
}
