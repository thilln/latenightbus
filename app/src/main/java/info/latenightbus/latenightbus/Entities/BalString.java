package info.latenightbus.latenightbus.Entities;

import java.sql.Date;

/**
 * Created by Paul Thillen on 08.09.2016.
 */
public class BalString {
    int id;
    String datum;
    String name;
    String location;
    String musik;
    String region;

    public BalString(int id, String datum, String name, String location, String musik, String region) {
        this.id = id;
        this.datum = datum;
        this.name = name;
        this.location = location;
        this.musik = musik;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
