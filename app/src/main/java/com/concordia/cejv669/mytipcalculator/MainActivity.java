package com.concordia.cejv669.mytipcalculator;


import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.btn_calculate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dn = findViewById(R.id.et_dinnernumber);
                EditText dp = findViewById(R.id.et_dinneprice);
                EditText tp = findViewById(R.id.et_tippercentage);
                TextView tv = findViewById(R.id.tv_result);
                InputMethodManager imm =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


                int dinner_number = Integer.parseInt(dn.getText().toString());
                double dinner_price = Double.parseDouble(dp.getText().toString());
                int tip_percentage = Integer.parseInt(tp.getText().toString());
                double gst = dinner_number*dinner_price*0.05;
                double qst = dinner_number*dinner_price*0.09975;
                double total_price = dinner_number*dinner_price+gst+qst;
                double tip_amount=dinner_number*dinner_price*tip_percentage/100;
                double tip_per_person=tip_amount/dinner_number;

                String output = String.format("%s %.2f","The price of the meal is $",dinner_number*dinner_price)
                        + String.format("%s %.2f","\nGST amount is $", gst)
                        + String.format("%s %.2f","\nQST amount is $", qst)
                        + String.format("%s %.2f","\nTotal price is $", total_price)
                        + String.format("%s %.2f","\nTip amount is $", tip_amount)
                        + String.format("%s %.2f","\nTip per person is $", tip_per_person);
                tv.setText(output);

            }
        });
    }
}
