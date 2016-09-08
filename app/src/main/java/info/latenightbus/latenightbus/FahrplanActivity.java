package info.latenightbus.latenightbus;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import info.latenightbus.latenightbus.DataAccess.BalAdapter;
import info.latenightbus.latenightbus.DataAccess.BalStringAdapter;
import info.latenightbus.latenightbus.Entities.Bal;
import info.latenightbus.latenightbus.Entities.BalString;
import info.latenightbus.latenightbus.Logic.JSONParser;

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


    Bal b1 = new Bal(1,new Date(2016,9,15),"Schoulufanksbal","Al Seerei, Diekirch","DJ Dee","nordstad");
    Bal b2 = new Bal(1,new Date(2016,9,17),"Back to school","Gymnase, Diekirch","DJ Dee","nordstad");
    Bal b3 = new Bal(1,new Date(2016,8,20),"Deppefest","Harel-Torchon","Torchon-Sängerspatzen","nordstad");
    Bal b4 = new Bal(1,new Date(2016,10,20),"Project X","Unbekannt","Money Boy","nordstad");
    Bal b5 = new Bal(1,new Date(2016,12,25),"Chreschtdach","Am Himmel","Jesus","nordstad");
    Bal b6 = new Bal(1,new Date(2016,12,24),"Helleg Owend","Doheem","Kanye West","nordstad");
    Bal b7 = new Bal(1,new Date(2016,9,16),"Flitts de Bal","Gilsdref","DJ Al","nordstad");
    Bal[] bal_lescht = {b1,b2,b3,b4,b5,b6,b7};
    ArrayList<Bal> baler = new ArrayList<>();
    ArrayList<BalString> baler2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrplan);
        new JSONParse().execute();
        BalStringAdapter bsa = new BalStringAdapter(this,baler2);
        ListView listView = (ListView) findViewById(R.id.fahrplanListView);
        listView.setAdapter(bsa);
        /*
        baler.addAll(Arrays.asList(bal_lescht));
        BalAdapter balAdapter = new BalAdapter(this, baler);
        ListView listView = (ListView) findViewById(R.id.fahrplanListView);
        listView.setAdapter(balAdapter);
        */

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FahrplanActivity.this);
            pDialog.setMessage("Getting data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser jParser = new JSONParser();

            //Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                fahrplan = json.getJSONArray(TAG_FAHRPLAN);

                for (int i = 0; i < fahrplan.length(); i++) {
                    JSONObject c = fahrplan.getJSONObject(i);
                    // Storing  JSON item in a Variable
                    int id = Integer.parseInt(c.getString(TAG_ID));
                    String name = c.getString(TAG_NAME);
                    String datum = c.getString(TAG_DATUM);
                    String platz = c.getString(TAG_PLATZ);
                    String musik = c.getString(TAG_MUSIK);
                    String region = c.getString(TAG_REGION);
                    BalString b = new BalString(id, name, datum, platz, musik, region);
                    baler2.add(b);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
