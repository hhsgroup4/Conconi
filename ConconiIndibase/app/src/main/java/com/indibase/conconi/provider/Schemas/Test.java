package com.indibase.conconi.provider.Schemas;

import android.provider.BaseColumns;

import com.indibase.conconi.provider.DatabaseSchema;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ralph on 5/26/2015.
 */
public class Test extends DatabaseSchema implements BaseColumns {
    @Override
    public String getTableName() {
        return "test";
    }

    @Override
    public String getCreateTableSql() {
        return "CREATE TABLE " + getTableName() + " ("
            + _ID + " INTEGER PRIMARY KEY"
            + ", creation TEXT"
            + ", deflection_point INTEGER"
            + ")";
    }

    @Override
    public String getInsertSql() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return "INSERT INTO " + getTableName() + " (creation, deflection_point) VALUES ("
                + "'" + dateFormat.format(date) + "'"
                + ", 5"
                + ")";
    }
}
