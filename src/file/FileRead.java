package file;
import app.Editor;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class FileRead
{
    public String readPath;

    public FileRead(String newPath)
    {
        readPath = Editor.getPathEditor() + "data/" + newPath;
    }

    public FileRead(File newPath)
    {
        readPath = newPath.getAbsolutePath();
    }

    public String[] FileReadData() throws IOException
    {
        FileReader fr = new FileReader(readPath);
        BufferedReader tr = new BufferedReader(fr);
        int lineCount = FileReadLines();
        String[] textData = new String[lineCount];
        for(int line=0; line < lineCount; line++)
        {
                textData[line] = tr.readLine();
        }
        tr.close();
        return textData;
    }

    private int FileReadLines() throws IOException
    {
        FileReader fr = new FileReader(readPath);
        BufferedReader bf = new BufferedReader(fr);
        int lineCount = 0;
        while(bf.readLine() != null)
        {
                lineCount+=1;
        }
        bf.close();
        return lineCount;
    }
    
    public ArrayList<String> FileReadList() throws IOException
    {
        FileReader fr = new FileReader(readPath);
        BufferedReader tr = new BufferedReader(fr);
        int lineCount = FileReadLines();
        ArrayList<String> textData = new ArrayList<String>();
        for(int line=0; line < lineCount; line++)
        {
                textData.add(tr.readLine());
        }
        tr.close();
        return textData;
    }

    public String getPath()
    {
        return readPath;
    }

}