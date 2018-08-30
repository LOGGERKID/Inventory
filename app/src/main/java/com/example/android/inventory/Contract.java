package com.example.android.inventory;

import android.provider.BaseColumns;

public final class Contract {
    public Contract() {};

    public static final class productEntry implements BaseColumns{

        public final static String TABLE_NAME = "products";

        public final static String  _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME = "productName";

        public final static String COLUMN_PRICE = "price";

        public final static String COLUMN_QUANTITY = "quantity";

        public final static String COLUMN_SUPPLIER_NAME = "supplierName";

        public final static String COLUMN_SUPPLIER_P_NO = "supplierPhone";

    }
}
