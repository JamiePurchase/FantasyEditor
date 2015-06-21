package project;

import app.Editor;
import file.FileService;
import file.FileWrite;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project
{
    private String ref, title, version;
    private Date update;
    private Map<String, ArrayList<Fragment>> fragments;
    
    public Project(String ref, String title, String version, String update)
    {
        this.ref = ref;
        this.title = title;
        this.version = version;
        try {this.update = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).parse(update);}
        catch (ParseException ex) {System.out.println(ex);}
        this.fragmentLoad();
    }
    
    private void fragmentLoad()
    {
        this.fragments = new HashMap<String, ArrayList<Fragment>>();
        String folder = Editor.getPathEditor() + "data/workspace/Test/";
        this.fragments.put("BOARDS", FileService.getFolder(new File(folder + "boards/"), ".jf1brd"));
        this.fragments.put("QUESTS", FileService.getFolder(new File(folder + "quests/"), ".jf1qst"));
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    private String[] getSaveData()
    {
        String[] data = new String[4];
        data[0] = "! PROJECT FILE - " + this.getRef() + " !";
        data[1] = this.getTitle();
        data[2] = this.getVersion();
        data[3] = this.getUpdateString();
        return data;
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public Date getUpdate()
    {
        return this.update;
    }
    
    public String getUpdateString()
    {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.getUpdate());
    }
    
    public String getVersion()
    {
        return this.version;
    }
    
    public void save()
    {
        FileWrite fw = new FileWrite("projects/" + this.getRef() + ".jf1pro", false);
        fw.FileWriteArray(this.getSaveData());
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void setUpdate()
    {
        this.update = new Date();
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
}