package com.indibase.conconi.provider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.indibase.conconi.provider.Schemas.Measurement;
import com.indibase.conconi.provider.Schemas.Test;

import java.util.ArrayList;

/**
 * Created by Ralph on 5/20/2015.
 */
public final class DbHelper extends SQLiteOpenHelper implements BaseColumns {

    public ArrayList<DatabaseSchema> schemas = new ArrayList<>();

    //If you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "conconi.db";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        schemas.add(new Test());
        schemas.add(new Measurement());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        for(DatabaseSchema schema : schemas)
            db.execSQL(schema.getCreateTableSql());

        for(DatabaseSchema schema : schemas)
            if(schema.getInsertSql() != null)
                db.execSQL(schema.getInsertSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        for(DatabaseSchema schema : schemas)
            db.execSQL(schema.getDeleteTableSQL());

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
