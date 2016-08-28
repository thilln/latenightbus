package info.latenightbus.latenightbus;

import android.app.ListActivity;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.latenightbus.latenightbus.DataAccess.BalAdapter;
import info.latenightbus.latenightbus.Entities.Bal;

public class FahrplanActivity extends AppCompatActivity {

    Bal b1 = new Bal(1,new Date(2016,9,15),"Schoulufanksbal","Al Seerei, Diekirch","DJ Dee");
    Bal b2 = new Bal(1,new Date(2016,9,17),"Back to school","Gymnase, Diekirch","DJ Dee");
    Bal b3 = new Bal(1,new Date(2016,8,20),"Deppefest","Harel-Torchon","Torchon-Sängerspatzen");
    Bal b4 = new Bal(1,new Date(2016,10,20),"Project X","Unbekannt","Money Boy");
    Bal b5 = new Bal(1,new Date(2016,12,25),"Chreschtdach","Am Himmel","Jesus");
    Bal b6 = new Bal(1,new Date(2016,12,24),"Helleg Owend","Doheem","Kanye West");
    Bal b7 = new Bal(1,new Date(2016,9,16),"Flitts de Bal","Gilsdref","DJ Al");
    Bal[] bal_lescht = {b1,b2,b3,b4,b5,b6,b7};

    String full_json ="";



    private void readStream(InputStream in) {
        //TODO
    }

    protected Bal[] getFahrplan(String region) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        try {
            url = new URL("http://paul.diekirch.org/app/connection.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }



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
