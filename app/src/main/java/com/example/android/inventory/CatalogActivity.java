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
        int i = 5;
        while(i>1) {
            ContentValues values = new ContentValues();
            values.put(productEntry.COLUMN_PRODUCT_NAME, "PAN -" + i);
            values.put(productEntry.COLUMN_PRICE, 5);
            values.put(productEntry.COLUMN_QUANTITY, 2);
            values.put(productEntry.COLUMN_SUPPLIER_NAME, "Udacity");
            values.put(productEntry.COLUMN_SUPPLIER_P_NO, 123456789*i);

            long rowID = db.insert(productEntry.TABLE_NAME, null, values);
            Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
        i--;
        }

    }

    @SuppressLint("SetTextI18n")
    private void displayDatabaseInfo() {
        ProductDBHelper mDbHelper = new ProductDBHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + productEntry.TABLE_NAME, null);
        int count = cursor.getCount();
        if (count == 0)
            insertDataToDatabase();

        String[] projection = {
                productEntry._ID,
                productEntry.COLUMN_PRODUCT_NAME,
                productEntry.COLUMN_SUPPLIER_P_NO};

        cursor = db.query(
                productEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.productDetails);
        try {

            displayView.setText("The Product table contains " + count + "\n\n");
            displayView.append(productEntry._ID + " - " +
                    productEntry.COLUMN_PRODUCT_NAME + " - " +
                    productEntry.COLUMN_SUPPLIER_P_NO + "\n");

            int idColumnIndex = cursor.getColumnIndex(productEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(productEntry.COLUMN_PRODUCT_NAME);
            int supplierColumnIndex = cursor.getColumnIndex(productEntry.COLUMN_SUPPLIER_P_NO);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProductName = cursor.getString(nameColumnIndex);
                String currentSupplierPNo = cursor.getString(supplierColumnIndex);
                        displayView.append(("\n" + currentID + " - " +
                        currentProductName + " - " +
                        currentSupplierPNo + " - " ));
            }
        } finally {
            cursor.close();
        }
    }
}
