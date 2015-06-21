package audio;

import java.util.ArrayList;

public class Soundtrack
{
    private String ref;
    private ArrayList<Track> tracks;
    
    public Soundtrack(String ref)
    {
        this.ref = ref;
        this.tracks = new ArrayList<Track>();
    }
    
    public void addTrack(Track track)
    {
        this.tracks.add(track);
    }
    
    public String getData()
    {
        return "";
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
}