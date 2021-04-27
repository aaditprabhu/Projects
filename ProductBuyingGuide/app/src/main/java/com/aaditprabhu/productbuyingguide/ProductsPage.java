package com.aaditprabhu.productbuyingguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ProductsPage extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //converting the string array into list
        List<String> techList = Arrays.asList(getResources().getStringArray(R.array.techList));

        //inflate the GUI with the Tech List
        setListAdapter(new ArrayAdapter<String>(this,R.layout.activity_products_page, R.id.techListTextView, techList));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){

        Intent itemIntent = new Intent();

        //create/open intent based on list selection
        switch (position){
            case 0:         //Phones
                itemIntent = new Intent(ProductsPage.this, TechListActivity.class);

                //add label text and resource id to itemIntent as string extras
                itemIntent.putExtra(TechListActivity.LBL_KEY, getResources().getString(R.string.txtPhone));
                itemIntent.putExtra(TechListActivity.ID_KEY, Integer.toString(R.drawable.android_vs_ios));
                itemIntent.putExtra(TechListActivity.BTN_KEY, "https://www.pcmag.com/picks/the-best-phones");
                break;
            case 1:         //Laptop
                itemIntent = new Intent(ProductsPage.this, TechListActivity.class);

                //add label text and resource id to itemIntent as string extras
                itemIntent.putExtra(TechListActivity.LBL_KEY, getResources().getString(R.string.txtLaptop));
                itemIntent.putExtra(TechListActivity.ID_KEY, Integer.toString(R.drawable.best_laptop));
                itemIntent.putExtra(TechListActivity.BTN_KEY, "https://www.laptopmag.com/reviews/best-laptops-1");
                break;
            case 2:         //Smart Home
                itemIntent = new Intent(ProductsPage.this, TechListActivity.class);

                //add label text and resource id to itemIntent as string extras
                itemIntent.putExtra(TechListActivity.LBL_KEY, getResources().getString(R.string.txtSmartHome));
                itemIntent.putExtra(TechListActivity.ID_KEY, Integer.toString(R.drawable.smart_home_devices));
                itemIntent.putExtra(TechListActivity.BTN_KEY, "https://www.pcmag.com/news/the-best-smart-home-devices-for-2020");
                break;

            default:
                Toast myToast = Toast.makeText(ProductsPage.this, "Invalid Choice Made!", Toast.LENGTH_LONG);
                myToast.show();
                itemIntent = null;
                break;
        }
        //start Activity using itemIntent
        if (itemIntent != null){
            startActivity(itemIntent);
        }

    }
}