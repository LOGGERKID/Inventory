package com.example.android.inventory;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventory.Contract.productEntry;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        displayDatabaseInfo();
    }

    private void insertDataToDatabase() {
        ProductDBHelper mDbHelper = new ProductDBHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(productEntry.COLUMN_PRODUCT_NAME,"PAN");
        values.put(productEntry.COLUMN_PRICE,5);
        values.put(productEntry.COLUMN_QUANTITY,2);
        values.put(productEntry.COLUMN_SUPPLIER_NAME,"Udacity");
        values.put(productEntry.COLUMN_SUPPLIER_P_NO,123456789);

        long rowID = db.insert(productEntry.TABLE_NAME,null,values);
        Toast.makeText(this, "Product saved with row id: " + rowID, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void displayDatabaseInfo() {
        ProductDBHelper mDbHelper = new ProductDBHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " + productEntry.TABLE_NAME, null);
        try {
            int count = cursor.getCount();
            if(count==0)
                insertDataToDatabase();

            TextView displayView = (TextView) findViewById(R.id.productDetails);
            displayView.setText("Number of rows in Products database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }
}
