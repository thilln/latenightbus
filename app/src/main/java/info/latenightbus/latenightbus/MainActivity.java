package info.latenightbus.latenightbus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare and initialize buttons
        ImageButton ib_nordstad = (ImageButton) findViewById(R.id.button_nordstad);
        ImageButton ib_nordspetzt = (ImageButton) findViewById(R.id.button_nordspetzt);
        ImageButton ib_atert = (ImageButton) findViewById(R.id.button_atert);
        ImageButton ib_all = (ImageButton) findViewById(R.id.button_all);
        final Context c = this;
        //set onClick Listeners
        ib_nordstad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNetworkAvailable()) {
                            Intent intent = new Intent(c, FahrplanActivity.class);
                            intent.putExtra("region",1);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Du muss mam Internet verbonnen sinn fir d'Fuerpläng opzeruffen", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        ib_nordspetzt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNetworkAvailable()) {
                            Intent intent = new Intent(c, FahrplanActivity.class);
                            intent.putExtra("region", 2);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Du muss mam Internet verbonnen sinn fir d'Fuerpläng opzeruffen", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        ib_atert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNetworkAvailable()) {
                            Intent intent = new Intent(c, FahrplanActivity.class);
                            intent.putExtra("region", 3);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Du muss mam Internet verbonnen sinn fir d'Fuerpläng opzeruffen", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        ib_all.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isNetworkAvailable()) {
                            Intent intent = new Intent(c, FahrplanActivity.class);
                            intent.putExtra("region", 0);
                            startActivity(intent);
                        } else {
                           Toast.makeText(getApplicationContext(), "Du muss mam Internet verbonnen sinn fir d'Fuerpläng opzeruffen", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
