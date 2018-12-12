package leo.awatin.com.e_seebudgetv3;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceFragment extends Fragment {

    DatabaseHelper myDb;
    TextView totalBalance;
    Button deleteAllData, viewAllData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_balance, container, false);

        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                startActivity(intent);
            }
        });
        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDb = new DatabaseHelper(getActivity());
         totalBalance = (TextView) getView().findViewById(R.id.textViewTotal);
         deleteAllData = (Button) getView().findViewById(R.id.button_deleteData);
         viewAllData = (Button) getView().findViewById(R.id.button_viewData);

        setBalance();
        deleteAllOfData();
        viewData();
    }


    public void setBalance(){
        Integer integerExpenses = myDb.getValueTotalExpenses();
        Integer integerIncome = myDb.getValueTotalIncome();
        Integer integerTotal = integerIncome - integerExpenses;
        totalBalance.setText(Integer.toString(integerTotal));
    }

    public void deleteAllOfData(){
        deleteAllData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    myDb.deleteAllData();
                    totalBalance.setText("0");
                    Toast.makeText(getActivity(),"All data is now lost!",Toast.LENGTH_LONG).show();
                };
            }
        );
    };

    public void viewData(){
        viewAllData.setOnClickListener(
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}


