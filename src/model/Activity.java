package model;


public class Activity implements Activity_IF{
    private int id;
    private String name;
    private int spid;
    private String location;
    private String description;
    
    private Shift_IF[] shifts;


    public Activity() {
    }

    public Activity(String name, int spid, String location, String description) {
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }

    public Activity(int id, String name, int spid, String location, String description) {
        this.id = id;
        this.name = name;
        this.spid = spid;
        this.location = location;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSpid() {
        return spid;
    }
    public void setSpid(int spid) {
        this.spid = spid;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
