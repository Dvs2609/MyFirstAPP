package com.example.myapplicatiom.db;

import android.provider.BaseColumns;

public final class database {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private database() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String _ID = "id";
        public static final String COLUMN_NAME_DNI = "dni";
        public static final String COLUMN_NAME_NOMBRE = "nombre";
        public static final String COLUMN_NAME_APELLIDO = "apellido";
        public static final String COLUMN_NAME_EDAD = "edad";
        public static final String COLUMN_NAME_DIRECCION = "direccion";
    }
}