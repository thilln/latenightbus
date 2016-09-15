package info.latenightbus.latenightbus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import info.latenightbus.latenightbus.DataAccess.BalStringAdapter;
import info.latenightbus.latenightbus.Entities.BalString;
import info.latenightbus.latenightbus.Logic.JSONParser;

public class FahrplanActivity extends AppCompatActivity {



    //URL to get JSON Array
    private static String all = "http://latenightbus.info/app/all.php";
    private static String nordstad = "http://latenightbus.info/app/nordstad.php";
    private static String nordspetzt = "http://latenightbus.info/app/nordspetzt.php";
    private static String atert = "http://latenightbus.info/app/atert.php";

    //JSON Node Names
    private static final String TAG_FAHRPLAN = "fahrplan";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_DATUM = "datum";
    private static final String TAG_PLATZ = "platz";
    private static final String TAG_MUSIK = "musik";
    private static final String TAG_REGION = "region";

    public static final int ALL_ID = 0;
    public static final int NORDSTAD_ID = 1;
    public static final int NORDSPETZT_ID = 2;
    public static final int ATERT_ID = 3;

    public static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    JSONArray fahrplan = null;
    ArrayList<BalString> baler = new ArrayList<>();
    int mRegion = ALL_ID;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrplan);
        Bundle b = this.getIntent().getExtras();
        mRegion = b.getInt("region");
        JSONObject json = null;
        try {
            json= new JSONParse().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        processJson(json);
        BalStringAdapter bsa = new BalStringAdapter(this, baler);
        ListView listView = (ListView) findViewById(R.id.fahrplanListView);
        listView.setAdapter(bsa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Date balDate = null;
                BalString mBal = baler.get(position);
                balDate = mBal.getDatum();
                Date today = new Date();
                if (balDate.getTime()-14*24*60*60*1000< today.getTime()) {
                    int clicked_id = baler.get(position).getId();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://paul.diekirch.org/wp-content/uploads/"
                            + clicked_id + ".pdf"));
                    startActivity(browserIntent);
                } else {
                    Toast.makeText(getApplicationContext(),"D'Fuerpläng fir ee Bal ginn ëmmer réicht " +
                            "2 Woche virdrun online gesat.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void processJson(JSONObject json) {
        try {
            // Getting JSON Array
            fahrplan = json.getJSONArray(TAG_FAHRPLAN);

            for (int i = 0; i < fahrplan.length(); i++) {
                JSONObject c = fahrplan.getJSONObject(i);
                // Storing  JSON item in a Variable
                int id = Integer.parseInt(c.getString(TAG_ID));
                String name = c.getString(TAG_NAME);
                String datum = c.getString(TAG_DATUM);
                Date d = df.parse(datum);
                String platz = c.getString(TAG_PLATZ);
                String musik = c.getString(TAG_MUSIK);
                String region = c.getString(TAG_REGION);
                BalString b = new BalString(id, name, d, platz, musik, region);
                baler.add(b);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONParser jParser = new JSONParser();
            JSONObject json = null;

            //Getting JSON from URL
            switch (mRegion) {
                case ALL_ID:
                    json = jParser.getJSONFromUrl(all);
                    break;
                case NORDSTAD_ID:
                    json = jParser.getJSONFromUrl(nordstad);
                    break;
                case NORDSPETZT_ID:
                    json = jParser.getJSONFromUrl(nordspetzt);
                    break;
                case ATERT_ID:
                    json = jParser.getJSONFromUrl(atert);
                    break;
                default:
                    json = jParser.getJSONFromUrl(all);
            }
            return json;
        }
    }
}
