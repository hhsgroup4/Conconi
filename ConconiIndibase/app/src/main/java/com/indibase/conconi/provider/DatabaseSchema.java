package com.indibase.conconi.provider;

import java.util.List;

/**
 * Created by Ralph on 5/26/2015.
 */
public abstract class DatabaseSchema {

    public abstract String getTableName();

    public abstract String getCreateTableSql();

    public String getInsertSql(){ return null; }

    public boolean canModifySingleRow(){
        return false;
    }

    public String getDeleteTableSQL(){
        return "DROP TABLE IF EXISTS " + this.getTableName();
    }
}
