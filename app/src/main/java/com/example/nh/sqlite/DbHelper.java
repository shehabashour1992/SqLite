package com.example.nh.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="shopsInfo";
    private static final String TABLE_SHOPS="shops";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_ADDRESS="address";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    // create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SHOPS_TABLE="CREATE TABLE "+TABLE_SHOPS+" ("+KEY_ID+
                " INTEGER PRIMARY KEY, "+KEY_NAME+" TEXT, "+KEY_ADDRESS+" TEXT)";
        db.execSQL(CREATE_SHOPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS"+TABLE_SHOPS);
        onCreate(db);}

    public void addShop(Shop shop)
    {
        // content values object are used to insert new rows into database tables.
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_ID,shop.getId());
        values.put(KEY_NAME,shop.getName());
        values.put(KEY_ADDRESS,shop.getAddress());

        db.insert(TABLE_SHOPS,null,values);
        db.close();
    }

    //cursors act as pointers to the data
    public Shop getShop(int id){
    SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.query(TABLE_SHOPS,new String[]{KEY_ID,KEY_NAME,KEY_ADDRESS},
                KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        Shop shop=null;
        if(cursor!=null){
            cursor.moveToFirst();
             shop=new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));
        }return shop;
    }

    public ArrayList<Shop> getAllShops(){
        ArrayList<Shop>shops=new ArrayList<Shop>();
        SQLiteDatabase dp=this.getReadableDatabase();
        String selectQuery="SELECT * FROM"+TABLE_SHOPS;

        Cursor cursor=dp.rawQuery(selectQuery,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                do {
                    Shop shop=new Shop();
                    shop.setId(Integer.parseInt(cursor.getString(0)));
                    shop.setName(cursor.getString(1));
                    shop.setAddress(cursor.getString(2));
                    shops.add(shop);
                }while (cursor.moveToNext());
            }
        }
        return shops;
    }
}







