/*package leo.awatin.com.e_seebudgetv3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter{
    List list = new ArrayList();
    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView VALUE, TIME, DATE, TYPE;


    }

    @Override
    public void add(Object object){
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutHandler layoutHandler;
        if (row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.VALUE = (TextView)row.findViewById(R.id.text_value);
            layoutHandler.TIME = (TextView)row.findViewById(R.id.text_time);
            layoutHandler.DATE = (TextView)row.findViewById(R.id.text_date);
            layoutHandler.TYPE = (TextView)row.findViewById(R.id.text_type);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)row.getTag();
        }
        DataProvider dataProvider=(DataProvider)this.getItem(position);
        layoutHandler.VALUE.setText(dataProvider.getValue());
        layoutHandler.TIME.setText(dataProvider.getTime());
        layoutHandler.DATE.setText(dataProvider.getDate());
        layoutHandler.TYPE.setText(dataProvider.getType());

        return row;

    }
}*/
