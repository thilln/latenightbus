package info.latenightbus.latenightbus.DataAccess;

/**
 * Created by Paul Thillen on 08.09.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.latenightbus.latenightbus.Entities.Bal;
import info.latenightbus.latenightbus.Entities.BalString;
import info.latenightbus.latenightbus.R;

public class BalStringAdapter extends ArrayAdapter<BalString> {
    public BalStringAdapter(Context context, ArrayList<BalString> baler) {
        super(context, 0, baler);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BalString bal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvDatum = (TextView) convertView.findViewById(R.id.datum);
        TextView tvPlatz = (TextView) convertView.findViewById(R.id.platz);
        ImageView ivLogo = (ImageView) convertView.findViewById(R.id.imageView);
        // Populate the data into the template view using the data object
        tvName.setText(bal.getName());
        tvDatum.setText(bal.writeDatum());
        tvPlatz.setText(bal.getLocation());
        switch (bal.getRegion()) {
            case "nordspetzt":
                ivLogo.setImageResource(R.drawable.logo_nordspetzt);
                break;
            case "atert":
                ivLogo.setImageResource(R.drawable.logo_atert);
                break;
            case "nordstad":
                ivLogo.setImageResource(R.drawable.logo_nordstad);
                break;
            default:
                break;
        }
        // Return the completed view to render on screen
        return convertView;
    }



}
