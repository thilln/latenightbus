package info.latenightbus.latenightbus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declare and initialize buttons
        ImageButton ib_nordstad = (ImageButton) findViewById(R.id.button_nordstad);
        ImageButton ib_nordspetzt = (ImageButton) findViewById(R.id.button_nordspetzt);
        ImageButton ib_atert = (ImageButton) findViewById(R.id.button_atert);
        final Context c = this;
        //set onClick Listeners
        ib_nordstad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(c, FahrplanActivity.class);
                        intent.putExtra("button_id",0);
                        startActivity(intent);
                    }
                }
        );
        ib_nordspetzt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(c, FahrplanActivity.class);
                        intent.putExtra("button_id",1);
                        startActivity(intent);
                    }
                }
        );
        ib_atert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(c, FahrplanActivity.class);
                        intent.putExtra("button_id",2);
                        startActivity(intent);
                    }
                }
        );

    }
}
