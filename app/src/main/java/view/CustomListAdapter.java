package view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appsolutions.cards.R;

/**
 * Created by Администратор on 13.11.2015.
 */
public class CustomListAdapter extends ArrayAdapter {
    private LayoutInflater inflanter;
    private String [] items;

    public CustomListAdapter(Activity activity, String [] items) {
        super(activity, R.layout.list_layout, items);
        inflanter = activity.getWindow().getLayoutInflater();
        this.items = items;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = inflanter.inflate(R.layout.list_layout, parent, false);
        TextView label = (TextView) rowView.findViewById(R.id.label);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        icon.setVisibility(ImageView.INVISIBLE); //temporary
        label.setText(items[position]);
        return rowView;
    }
}
