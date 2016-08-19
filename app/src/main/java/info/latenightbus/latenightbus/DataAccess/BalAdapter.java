package info.latenightbus.latenightbus.DataAccess;
/**
 * Created by Paul Thillen on 19.08.2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.latenightbus.latenightbus.Entities.Bal;
import info.latenightbus.latenightbus.R;

public class BalAdapter extends ArrayAdapter<Bal> {
    public BalAdapter(Context context, ArrayList<Bal> baler) {
        super(context, 0, baler);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Bal bal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bal_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvDatum = (TextView) convertView.findViewById(R.id.datum);
        TextView tvPlatz = (TextView) convertView.findViewById(R.id.platz);
        // Populate the data into the template view using the data object
        tvName.setText(bal.getName());
        tvDatum.setText(bal.getDate().toString());
        tvPlatz.setText(bal.getLocation());
        // Return the completed view to render on screen
        return convertView;
    }

}
