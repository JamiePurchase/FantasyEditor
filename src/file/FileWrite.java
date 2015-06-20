package file;

import app.Editor;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileWrite
{
    // File Writing
    private String writePath;
    private boolean writeAppend = false;
    private String writeBreak;

    public FileWrite(String newPath, boolean newAppend)
    {
        writePath = Editor.getPath() + "data/" + newPath;
        writeAppend = newAppend;
        writeBreak = System.getProperty("line.separator");
    }

    public void FileWriteLine(String textLine) throws IOException
    {
        FileWriter write = new FileWriter(writePath, writeAppend);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s" + "%n", textLine);
        print_line.close();
    }

    public void FileWriteArray(String[] textLines)
    {
        if(writeAppend == true) {FileWriteArrayAppend(textLines);}
        else {FileWriteArrayOverwrite(textLines);}
    }

    public void FileWriteArrayAppend(String[] textLines)
    {
        for(int line = 0; line < textLines.length; line++)
        {
            try {FileWriteLine(textLines[line]);}
            catch (IOException e) {e.printStackTrace();}
        }
    }

    public void FileWriteArrayOverwrite(String[] textLines)
    {
        String textData = "";
        for(int line = 0; line < textLines.length; line++)
        {
                textData = textData + textLines[line];
                if(line < textLines.length - 1) {textData = textData + writeBreak;}
        }
        try {FileWriteLine(textData);}
        catch (IOException e) {e.printStackTrace();}
    }

}