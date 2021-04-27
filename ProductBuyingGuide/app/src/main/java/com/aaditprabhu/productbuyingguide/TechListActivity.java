package com.aaditprabhu.productbuyingguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TechListActivity extends AppCompatActivity {

    public static final String ID_KEY = "RES_ID";
    public static final String LBL_KEY = "LABEL";
    public static final String BTN_KEY = "BUTTON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_list);

        //get extras from intent
        Intent myIntent = getIntent();
        Bundle myExtras = myIntent.getExtras();

        if (myExtras != null){      //verify that there are extras with intent
            //get String extras with key == LBL_KEY
            String res_label = myExtras.getString(LBL_KEY);

            //display the label string
            final TextView listTitleTextView = (TextView)findViewById(R.id.listTitleTextView);
            listTitleTextView.setText(res_label);

            //get string extras with key == ID_KEY
            String image_id = myExtras.getString(ID_KEY);

            //convert resource ID from string to integer
            int imageID = Integer.parseInt(image_id);

            /*
             * In the listImageView
             * display the image with imageID resource ID
             * change the content description to res_label string
             * */

            final ImageView listImageView = (ImageView)findViewById(R.id.listImageView);
            listImageView.setImageResource(imageID);
            listImageView.setContentDescription(res_label);

            //parse URI to Button
            final String URL = myExtras.getString(BTN_KEY);
            final Button listButton = (Button)findViewById(R.id.listButton);

            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String final_url = URL;

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(final_url));
                    startActivity(intent);
                }
            });
        }
    }
}