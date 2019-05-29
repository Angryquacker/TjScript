import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class Interpret {
  private String[] commands;
  private String data = "";
  private CommandLinker linker = new CommandLinker();

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
      
      linker.add(this.commands[i]);
    }
  }

  public void execute() {
    for (int i = 0;i < this.linker.lines.size();i++) {
      Execute.run(this.linker.get(i));
    }
  }
}
