package info.latenightbus.latenightbus.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Paul Thillen on 08.09.2016.
 */
public class BalString {
    int id;
    Date datum;
    String name;
    String location;
    String musik;
    String region;

    public BalString(int id, String name, Date datum, String location, String musik, String region) {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String writeDatum() {
        String dach = /*translateLUX(*/new SimpleDateFormat("EEEE").format(datum)+", ";
        String teil2 = new SimpleDateFormat("d.M.yy").format(datum);
        return dach+teil2;
    }

    private String translateLUX(String day_english) {
        String result=null;
        switch (day_english) {
            case "Mon":
                result = "Méindes";
                break;
            case "Tue":
                result = "Dënsdes";
                break;
            case "Wed":
                result = "Mëttwochs";
                break;
            case "Thu":
                result = "Donneschdes";
                break;
            case "Fri":
                result = "Freides";
                break;
            case "Sat":
                result = "Samsdes";
                break;
            case "Sun":
                result = "Sonndes";
                break;
            default:
                result = "error";
                break;
        }
        return result;

    }
}
