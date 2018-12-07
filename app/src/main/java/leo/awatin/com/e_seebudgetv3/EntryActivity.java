package leo.awatin.com.e_seebudgetv3;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EntryActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editValue, editTime, editDate;
    Button btnAddEntry, btnViewAll;
    TextView isIncomeOrExpense;
    Switch aSwitch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_entry );
        this.setTitle( "New Entry" );

        myDb = new DatabaseHelper(this);

        editValue = (EditText)findViewById(R.id.editText_Value);
        editTime = (EditText)findViewById(R.id.editText_Time);
        editDate = (EditText)findViewById(R.id.editText_Date);
        btnAddEntry = (Button)findViewById(R.id.button_save);
        btnViewAll= (Button)findViewById(R.id.button_viewall);
        isIncomeOrExpense = (TextView)findViewById(R.id.textIsIncomeOrExpense);
        aSwitch = (Switch)findViewById(R.id.switch1); ////////////////////////////////adding switch
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==false)
                     isIncomeOrExpense.setText("Income");
                else
                     isIncomeOrExpense.setText("Expense");
                }
        });
        AddEntry();
        viewAll();
    }

    public void AddEntry(){
        btnAddEntry.setOnClickListener(
                    new View.OnClickListener (){
                        @Override
                        public void onClick(View v){
                            boolean isInserted = myDb.insertData(editValue.getText().toString(),
                                    editTime.getText().toString(),
                                    editDate.getText().toString(),
                                    isIncomeOrExpense.getText().toString());
                            if(isInserted == true)
                                Toast.makeText(EntryActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(EntryActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                            startActivity(intent);

                        }
                    }
        );

    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id: "+ res.getString(0)+"\n");
                            buffer.append("Value: PHP"+ res.getString(1)+"\n");
                            buffer.append("Time: "+ res.getString(2)+"\n");
                            buffer.append("Date: "+ res.getString(3)+"\n");
                            buffer.append("Type: "+ res.getString(4)+"\n\n");
                        }
                        showMessage("Entries",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}
