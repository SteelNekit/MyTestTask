import Utils.MyFileManager;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        MyFileManager fileManager = new MyFileManager();
        fileManager.fillTargetFile();
        fileManager.doYourJob();
    }

}

