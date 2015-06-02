package com.indibase.conconi.provider.schemas;

import android.provider.BaseColumns;

import com.indibase.conconi.provider.DatabaseSchema;

import java.util.HashMap;

/**
 * Created by Ralph on 5/26/2015.
 */
public class Measurement extends DatabaseSchema implements BaseColumns{

    @Override
    public boolean canModifySingleRow(){
        return true;
    }

    @Override
    public String getCreateTableSql() {
        return "CREATE TABLE `" + getTableName() + "` ("
                + _ID + " INTEGER PRIMARY KEY"
                + ", `test_id` INTEGER"
                + ", `second` INTEGER"
                + ", `bpm` INTEGER"
                + ", FOREIGN KEY(`test_id`) REFERENCES Test(`_id`)"
                + ")";
    }

    @Override
    public String getInsertSql() {
        return "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 5, 100)";
    }
}
