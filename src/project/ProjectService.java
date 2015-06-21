package project;

import file.FileRead;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectService
{
    
    public static Project getProject(String ref)
    {
        try
        {
            FileRead fr = new FileRead("projects/" + ref + ".jf1pro");
            String[] project = fr.FileReadData();
            return new Project(ref, project[1], project[2], project[3]);
        }
        catch (IOException ex) {System.out.println(ex);}
        return null;
    }
    
}