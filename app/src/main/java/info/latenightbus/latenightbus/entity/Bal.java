package info.latenightbus.latenightbus.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Paul Thillen on 18.08.2016.
 */
public class Bal implements Serializable, Comparable {
    final int id;
    Date date;
    String name;
    String location;
    String music;


    public Bal(int id) {
        this.id = id;
    }


    public Bal(int id, Date date, String name, String location, String music) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.location = location;
        this.music = music;
    }

    @Override
    public int compareTo(Object another) {
        Bal other = null;
        if (another!=null) {
            other = (Bal) another;
        } else {
            return 0;
        }
        return this.date.compareTo(other.date);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }
}
