package info.latenightbus.latenightbus;

import android.app.ListActivity;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    String full_json ="{\"fahrplan\":[{\"id\":\"1\",\"name\":\"Last Summer Dance\",\"datum\":\"2016-08-27\",\"platz\":\"Erpeldange, Schlasspark\",\"musik\":\"Bands\",\"region\":\"nordstad\"},{\"id\":\"2\",\"name\":\"Nordic Rock Festival\",\"datum\":\"2016-09-10\",\"platz\":\"Vianden, Larei\",\"musik\":\"Bands\",\"region\":\"nordstad\"},{\"id\":\"3\",\"name\":\"Flitts de Bal\",\"datum\":\"2016-09-16\",\"platz\":\"Gilsdref, Fussbalsterrain\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"4\",\"name\":\"Fox'e Bal\",\"datum\":\"2016-09-24\",\"platz\":\"Bastendorf\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"5\",\"name\":\"Heinekenparty\",\"datum\":\"2016-10-01\",\"platz\":\"Hoscheid\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"6\",\"name\":\"Haxen-Owend\",\"datum\":\"2016-10-01\",\"platz\":\"Bettendorf\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"7\",\"name\":\"Oktoberfest\",\"datum\":\"2016-10-28\",\"platz\":\"Bastendorf\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"8\",\"name\":\"Halloween Bal\",\"datum\":\"2016-10-31\",\"platz\":\"Diekirch\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"9\",\"name\":\"Limousins Bal\",\"datum\":\"2016-11-25\",\"platz\":\"Cruchten\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"10\",\"name\":\"I love 20\",\"datum\":\"2016-12-19\",\"platz\":\"Nommern\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"11\",\"name\":\"Sylvester Bal\",\"datum\":\"2016-12-31\",\"platz\":\"Diekirch, Al Seerei\",\"musik\":\"?\",\"region\":\"nordstad\"},{\"id\":\"12\",\"name\":\"Ballermann Mashup\",\"datum\":\"2016-09-16\",\"platz\":\"Mertzig\",\"musik\":\"DJ AL\",\"region\":\"nordstad\"},{\"id\":\"13\",\"name\":\"Beachparty\",\"datum\":\"2016-08-26\",\"platz\":\"Elwen\",\"musik\":\"\\tMissRoxx, DEE, House a Holics\",\"region\":\"nordspetzt\"},{\"id\":\"14\",\"name\":\"Live! zu Harel\",\"datum\":\"2016-09-02\",\"platz\":\"Harlange\",\"musik\":\"DJ Blue, D\\u00ebppeg\\u00e9isser, Dizz Picture\",\"region\":\"nordspetzt\"},{\"id\":\"15\",\"name\":\"Summernights\",\"datum\":\"2016-09-02\",\"platz\":\"Weiswampach\",\"musik\":\"DJ Paul, DJ Dee, The Diamonds\",\"region\":\"nordspetzt\"},{\"id\":\"16\",\"name\":\"Summernights\",\"datum\":\"2016-09-03\",\"platz\":\"Weiswampach\",\"musik\":\"\\tDJs Purple, ND\",\"region\":\"nordspetzt\"},{\"id\":\"17\",\"name\":\"Open-Air Funky Donkey Festival\",\"datum\":\"2016-09-10\",\"platz\":\"Cli\\u00e4rref\",\"musik\":\"Seed to Tree, De L\\u00e4b, Fox\",\"region\":\"nordspetzt\"},{\"id\":\"18\",\"name\":\"Red Cup Party\",\"datum\":\"2016-09-17\",\"platz\":\"Elwen\",\"musik\":\"House a Holics, DJ L-Flx\",\"region\":\"nordspetzt\"},{\"id\":\"19\",\"name\":\"Baal\",\"datum\":\"2016-09-23\",\"platz\":\"Cli\\u00e4rref\",\"musik\":\"DJ Dee, DJ Tyler-K\",\"region\":\"nordspetzt\"},{\"id\":\"20\",\"name\":\"1 Euro Bal\",\"datum\":\"2016-09-24\",\"platz\":\"Huldang\",\"musik\":\"No Limit\",\"region\":\"nordspetzt\"},{\"id\":\"21\",\"name\":\"Scheierfest\",\"datum\":\"2016-09-30\",\"platz\":\"Knapphouschent\",\"musik\":\"\",\"region\":\"nordspetzt\"},{\"id\":\"22\",\"name\":\"Bulmer's Party\",\"datum\":\"2016-10-08\",\"platz\":\"Boxer\",\"musik\":\"Under Pressure\",\"region\":\"nordspetzt\"},{\"id\":\"23\",\"name\":\"Mais Bal\",\"datum\":\"2016-10-15\",\"platz\":\"Niderwampach\",\"musik\":\"DJ Dee, The Diamonds, DJ ND\",\"region\":\"nordspetzt\"},{\"id\":\"24\",\"name\":\"Halloween Bal\",\"datum\":\"2016-10-31\",\"platz\":\"Hepperdang\",\"musik\":\"DJ Fighter, DJ Giant, Nightlife DJ's\",\"region\":\"nordspetzt\"},{\"id\":\"25\",\"name\":\"Haupesch Bal\",\"datum\":\"2016-11-05\",\"platz\":\"Munzen\",\"musik\":\"\",\"region\":\"nordspetzt\"},{\"id\":\"26\",\"name\":\"Hello Kitty Party\",\"datum\":\"2016-11-12\",\"platz\":\"Boxer\",\"musik\":\"\",\"region\":\"nordspetzt\"},{\"id\":\"27\",\"name\":\"Corona-Party\",\"datum\":\"2016-11-19\",\"platz\":\"Helzen\",\"musik\":\"Providers\",\"region\":\"nordspetzt\"},{\"id\":\"28\",\"name\":\"Santa Party\",\"datum\":\"2016-12-03\",\"platz\":\"Huldang\",\"musik\":\"D'Belsch Jecke & DJ Project\",\"region\":\"nordspetzt\"},{\"id\":\"29\",\"name\":\"Hugobaal\",\"datum\":\"2016-12-10\",\"platz\":\"Hepperdang\",\"musik\":\"\",\"region\":\"nordspetzt\"},{\"id\":\"30\",\"name\":\"Chrestbaal\",\"datum\":\"2016-12-23\",\"platz\":\"Cli\\u00e4rref\",\"musik\":\"\\tD'Belsch Jecke\",\"region\":\"nordspetzt\"},{\"id\":\"31\",\"name\":\"Chrestbal\",\"datum\":\"2016-12-26\",\"platz\":\"Hengischt\",\"musik\":\"\",\"region\":\"nordspetzt\"},{\"id\":\"32\",\"name\":\"Neijoersdisco\",\"datum\":\"2017-01-01\",\"platz\":\"Helzen\",\"musik\":\"DJ Paul, DJ Giant\",\"region\":\"nordspetzt\"},{\"id\":\"33\",\"name\":\"Franzi Party\",\"datum\":\"2016-09-10\",\"platz\":\"Boevange-Atert\",\"musik\":\"DJ AL\",\"region\":\"atert\"},{\"id\":\"34\",\"name\":\"Kroopemannsbal\",\"datum\":\"2016-09-17\",\"platz\":\"Reiden\",\"musik\":\"Danzorchester\",\"region\":\"atert\"},{\"id\":\"35\",\"name\":\"Oktoberfest\",\"datum\":\"2016-10-29\",\"platz\":\"Arsdorf\",\"musik\":\"DJ Giant, Nightlife DJs\",\"region\":\"atert\"},{\"id\":\"36\",\"name\":\"Elloween Bal\",\"datum\":\"2016-10-31\",\"platz\":\"Ell\",\"musik\":\"\",\"region\":\"atert\"},{\"id\":\"37\",\"name\":\"Globetrotters\",\"datum\":\"2016-11-05\",\"platz\":\"Folscht\",\"musik\":\"DJ Giant\",\"region\":\"atert\"},{\"id\":\"38\",\"name\":\"Freck den Houseker\",\"datum\":\"2016-12-16\",\"platz\":\"Ell\",\"musik\":\"DJ Dee, Diamonds\",\"region\":\"atert\"},{\"id\":\"39\",\"name\":\"XX-Mass Party\",\"datum\":\"2016-12-25\",\"platz\":\"Ospern\",\"musik\":\"DJ Dany Gold\",\"region\":\"atert\"},{\"id\":\"40\",\"name\":\"XX-Mass Party\",\"datum\":\"2016-12-26\",\"platz\":\"Ospern\",\"musik\":\"Under Pressure\",\"region\":\"atert\"},{\"id\":\"41\",\"name\":\"Den 1. Bal\",\"datum\":\"2017-01-07\",\"platz\":\"Holz\",\"musik\":\"DJ Giant\",\"region\":\"atert\"}]}";


    @NonNull
    private String readStream(InputStream in) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        return responseStrBuilder.toString();
    }

    protected String getFahrplan() {
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream in = null;
        String json = null;
        try {
            url = new URL("http://paul.diekirch.org/app/connection.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            json = readStream(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return json;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahrplan2);
        TextView tv = (TextView) findViewById(R.id.tv_json);
        tv.setText(getFahrplan());
    }


}
