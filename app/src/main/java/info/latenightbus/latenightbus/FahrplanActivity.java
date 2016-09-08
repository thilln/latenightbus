package info.latenightbus.latenightbus;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import info.latenightbus.latenightbus.DataAccess.BalAdapter;
import info.latenightbus.latenightbus.Entities.Bal;

public class FahrplanActivity extends AppCompatActivity {

    //URL to get JSON Array
    private static String url = "http://paul.diekirch.org/app/JSON/index2.json";
    //JSON Node Names
    private static final String TAG_FAHRPLAN = "fahrplan";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DATUM = "datum";
    private static final String TAG_PLATZ = "platz";
    private static final String TAG_MUSIK = "musik";
    private static final String TAG_REGION = "region";

    JSONArray fahrplan = null;


    Bal b1 = new Bal(1,new Date(2016,9,15),"Schoulufanksbal","Al Seerei, Diekirch","DJ Dee");
    Bal b2 = new Bal(1,new Date(2016,9,17),"Back to school","Gymnase, Diekirch","DJ Dee");
    Bal b3 = new Bal(1,new Date(2016,8,20),"Deppefest","Harel-Torchon","Torchon-Sängerspatzen");
    Bal b4 = new Bal(1,new Date(2016,10,20),"Project X","Unbekannt","Money Boy");
    Bal b5 = new Bal(1,new Date(2016,12,25),"Chreschtdach","Am Himmel","Jesus");
    Bal b6 = new Bal(1,new Date(2016,12,24),"Helleg Owend","Doheem","Kanye West");
    Bal b7 = new Bal(1,new Date(2016,9,16),"Flitts de Bal","Gilsdref","DJ Al");
    Bal[] bal_lescht = {b1,b2,b3,b4,b5,b6,b7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrplan);
        ArrayList<Bal> baler = new ArrayList<>(Arrays.asList(bal_lescht));
        BalAdapter balAdapter = new BalAdapter(this, baler);
        ListView listView = (ListView) findViewById(R.id.fahrplanListView);
        listView.setAdapter(balAdapter);
    }


}
