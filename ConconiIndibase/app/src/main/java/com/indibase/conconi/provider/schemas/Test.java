package com.indibase.conconi.provider.schemas;

import android.provider.BaseColumns;

import com.indibase.conconi.provider.DatabaseSchema;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ralph on 5/26/2015.
 */
public class Test extends DatabaseSchema implements BaseColumns {

    @Override
    public boolean canModifySingleRow(){
        return true;
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
        return "INSERT INTO `" + getTableName() + "` (`creation`, `deflection_point`) VALUES ('" + dateFormat.format(date) + "', 170)";
    }
}
