package com.example.nh.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
/*
A-storage Internal(device) :
1-shared preference
2-internal storage (phone)
3-external storage(memory card)
4-SQlite

B-External(server)
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper helper=new DbHelper(this);
        helper.addShop(new Shop(1,"shop1","address1"));// add data once to database (primary key)
        helper.addShop(new Shop(2,"shop2","address2"));// add data to database
        helper.addShop(new Shop(3,"shop3","address3"));// add data to database
        Shop shop1=helper.getShop(1);// retrieve data

        Log.e("shop1",shop1.getId()+"   "+shop1.getName()+"     "+shop1.getAddress()); //print row 1 in log file

    }
}
