/*package leo.awatin.com.e_seebudgetv3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Datalist extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper myDb;
    ListView listView;
    Cursor cursor;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datalist);
        listView=(ListView)findViewById(R.id.list_view);
        myDb = new DatabaseHelper(this);
        sqLiteDatabase=myDb.getReadableDatabase();
        cursor=myDb.getAllData();
        listAdapter=new ListAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listAdapter);

    if (cursor.moveToFirst()){



        do {
            String value,time,date,type;
            value=cursor.getString(1);
            time=cursor.getString(2);
            date=cursor.getString(3);
            type=cursor.getString(4);
            DataProvider dataProvider=new DataProvider(value, time, date, type);
            listAdapter.add(dataProvider);

        }
        while (cursor.moveToFirst());



        }
    }
}*/
