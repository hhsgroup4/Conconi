package com.indibase.conconi.provider.Schemas;

import android.provider.BaseColumns;

import com.indibase.conconi.provider.DatabaseSchema;

/**
 * Created by Ralph on 5/26/2015.
 */
public class Measurement extends DatabaseSchema implements BaseColumns{
    @Override
    public String getTableName() {
        return "measurement";
    }

    @Override
    public String getCreateTableSql() {
        return "CREATE TABLE " + getTableName() + " ("
                + _ID + " INTEGER PRIMARY KEY"
                + ", test_id INTEGER"
                + ", second INTEGER"
                + ", bpm INTEGER"
                + ", FOREIGN KEY(test_id) REFERENCES test(_id)"
                + ")";
    }
}
