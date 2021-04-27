package com.aaditprabhu.splitthebill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BillCalc extends AppCompatActivity {

    private int numberOfMembers;
    private double totalBillAmount;
    private double splitAmount;
    private String rateChoice;
    private double tipValue;
    private double finalTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_calc);

        //Button Reference
        final Button btnSplit = (Button)findViewById(R.id.btnSplit);
        assert btnSplit != null;

        btnSplit.setOnClickListener(new View.OnClickListener() {

            final EditText membersEditText = (EditText)findViewById(R.id.membersEditText);
            final EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
            final Spinner rateSpinner = (Spinner)findViewById(R.id.rateSpinner);
            final TextView resultTextView = (TextView)findViewById(R.id.resultTextView);
            final TextView commentTextView = (TextView)findViewById(R.id.commentTextView);

            @Override
            public void onClick(View v) {

                //get number of group members
                String groupMembers = membersEditText.getText().toString();

                //get total bill amount
                String billAmount = amountEditText.getText().toString();

                //get rating value
                rateChoice = rateSpinner.getSelectedItem().toString();

                //set tip value
                if (rateChoice.equals("Excellent Service")){
                    tipValue = 0.20;
                    commentTextView.setText("One of the best meals ever!  I will recommend this place to everyone I know!");
                }
                else if (rateChoice.equals("Average Service")){
                    tipValue = 0.15;
                    commentTextView.setText("Everything was OK.");
                }
                else{
                    tipValue = 0.05;
                    commentTextView.setText("Awful!  The worst!  I can't wait to give negative reviews on Yelp!");
                }

                //calculate bill split
                    //convert string to integer
                    numberOfMembers = Integer.parseInt(groupMembers);

                    //convert string to double
                    totalBillAmount = Double.parseDouble(billAmount);

                    finalTip = totalBillAmount * tipValue;
                    splitAmount = (totalBillAmount + finalTip) / numberOfMembers;

                    //currency format
                    DecimalFormat currencyFormat = new DecimalFormat("$###,###.##");

                    //printing the output
                    String outputString = "Bill split between "+numberOfMembers+" members is "
                            +currencyFormat.format(splitAmount);
                    resultTextView.setText(outputString);

            }
        });
    }
}