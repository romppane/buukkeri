package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Activity")
public class Activity implements Activity_IF{
    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;
    @Column(name="Name")
    private String name;

    @ManyToOne
    @JoinColumn(name="SP_ID")
    private SP sp;

    @Column(name="Location")
    private String location;
    @Column(name="Description")
    private String description;





    public Activity() {
    }
    public Activity(int id, String name, SP sp, String location, String description) {
        this.id = id;
        this.name = name;
        this.sp = sp;
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
    public SP getSp() {
        return sp;
    }
    public void setSp(SP sp) {
        this.sp = sp;
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
