package com.indibase.conconi.provider;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Created by Ralph on 5/20/2015.
 */
public final class DbHelper extends SQLiteOpenHelper implements BaseColumns {

    public ArrayList<DatabaseSchema> schemas = new ArrayList<>();

    //If you modify any file in the schemes package, you must increment the database version
    public static final int DATABASE_VERSION = 16;

    public static final String DATABASE_NAME = "conconi.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        schemas = getSchemas(context);
    }

    private ArrayList<DatabaseSchema> getSchemas(Context context) {
        ArrayList<DatabaseSchema> schemas = new ArrayList<>();
        String packageName = this.getClass().getPackage().getName();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
                String identifier = iter.nextElement();
                if (identifier.startsWith(packageName + ".schemas")) {
                    Class cls = Class.forName(identifier);
                    DatabaseSchema schema = (DatabaseSchema) cls.newInstance();
                    schemas.add(schema);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schemas;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        for (DatabaseSchema schema : schemas) {
            db.execSQL(schema.getCreateTableSql());
            if (!schema.getCreateViewSql().equals(""))
                db.execSQL(schema.getCreateViewSql());
        }

        for (DatabaseSchema schema : schemas) {
            if (!schema.getInsertSql().equals(""))
                db.execSQL(schema.getInsertSql());

//only for testdata max 500 rows insert in one statement
            if (!schema.getInsertSql2().equals(""))
                db.execSQL(schema.getInsertSql2());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        for (DatabaseSchema schema : schemas) {
            db.execSQL(schema.getDeleteTableSql());
            if (!schema.getDeleteViewSql().equals(""))
                db.execSQL(schema.getDeleteViewSql());
        }

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
