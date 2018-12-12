package leo.awatin.com.e_seebudgetv3;

import android.content.Intent;
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

public class BalanceFragment extends Fragment {

    DatabaseHelper myDb;
    TextView totalBalance;

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
        setBalance();
    }

    public void setBalance(){
        Integer integerExpenses = myDb.getValueTotalExpenses();
        Integer integerIncome = myDb.getValueTotalIncome();
        Integer integerTotal = integerIncome - integerExpenses;
        totalBalance.setText(Integer.toString(integerTotal));
    }

    /*
    public void getBalance(){
        btnTotal.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v){
                                            Integer integerExpenses = myDb.getValueTotalExpenses();
                                            Integer integerIncome = myDb.getValueTotalIncome();
                                            Integer integerTotal = integerIncome - integerExpenses;
                                            total.setText(Integer.toString(integerTotal));
                                        }
                                    }
        );
    }
    */


}


