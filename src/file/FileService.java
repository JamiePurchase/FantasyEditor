package file;

import java.io.File;
import java.util.ArrayList;
import project.Fragment;

public class FileService
{
    public static String getExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
	
    public static ArrayList<Fragment> getFolder(final File folder, String type)
    {
        // Debug
        System.out.println("Loading '" + type + "' files in " + folder);
        
        ArrayList<Fragment> result = new ArrayList<Fragment>();
        File[] filesList = folder.listFiles();
        for(File f : filesList)
        {
            System.out.println(f.getName());
            if(f.isFile() && getExtension(f).equals(type))
            {
                result.add(new Fragment(f.getName(), type, f.getPath()));
            }
        }
        return result;
    }
    
}