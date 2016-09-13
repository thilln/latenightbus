package info.latenightbus.latenightbus.Entities;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Paul Thillen on 18.08.2016.
 */
public class Bal implements Serializable, Comparable {
    final int id;
    Date datum;
    String name;
    String location;
    String musik;
    String region;


    public Bal(int id) {
        this.id = id;
    }


    public Bal(int id, Date datum, String name, String location, String musik, String region) {
        this.id = id;
        this.datum = datum;
        this.name = name;
        this.location = location;
        this.musik = musik;
        this.region = region;
    }

    @Override
    public int compareTo(Object another) {
        Bal other = null;
        if (another!=null) {
            other = (Bal) another;
        } else {
            return 0;
        }
        return this.datum.compareTo(other.datum);
    }

    public int getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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

    public String getMusik() {
        return musik;
    }

    public void setMusik(String musik) {
        this.musik = musik;
    }
}
