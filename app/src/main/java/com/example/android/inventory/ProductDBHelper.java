package com.example.android.inventory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import com.example.android.inventory.Contract.productEntry;

public class ProductDBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME= "inventory.db";
    public final static int DATABASE_VERSiON = 1;

    public ProductDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSiON);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATES_PRODUCT_TABLE = "CREATE TABLE " + productEntry.TABLE_NAME + " ("
                + productEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + productEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + productEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + productEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 1, "
                + productEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + productEntry.COLUMN_SUPPLIER_P_NO + " INTEGER NOT NULL);";

        db.execSQL(CREATES_PRODUCT_TABLE);

        String INPUT_SAMPLE_DATA = "INSERT INTO " + productEntry.TABLE_NAME +" VALUES (PAN,5,1,Udacity,123456789);";
        //db.execSQL(INPUT_SAMPLE_DATA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
