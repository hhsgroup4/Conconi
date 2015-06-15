package com.indibase.conconi.provider;

import java.util.HashMap;

/**
 * Created by Ralph on 5/26/2015.
 */
public abstract class DatabaseSchema {

    public String getTableName(){
        String classIdentifier = this.getClass().getName();
        return classIdentifier.substring(classIdentifier.lastIndexOf(".") + 1).toLowerCase();
    }

    public abstract String getCreateTableSql();

    public String getInsertSql(){ return ""; }
//only for testdata max 500 rows insert in one statement
    public String getInsertSql2(){ return ""; }

    public HashMap<String, String> getViewsHashMap(){ return new HashMap<>(); }

    public String getViewById(int id){
        int i = 0;
        for(String key : getViewsHashMap().keySet()) {
            if (i == id)
                return key;
            i++;
        }
        return null;
    }

    public String getCreateViewSql(){
        String insertViewSql = "";
        for(String value : getViewsHashMap().values())
            insertViewSql += value;

        return insertViewSql;
    }

    public String getDeleteViewSql(){
        String deleteViewSql = "";
        if(getViewsHashMap() != null){
            for (String key : getViewsHashMap().keySet())
               deleteViewSql += "DROP VIEW `" + key + "`;";
        }
        return deleteViewSql;
    }

    public boolean canModifySingleRow(){
        return false;
    }

    public String getDeleteTableSql(){
        return "DROP TABLE IF EXISTS `" + this.getTableName() + "`";
    }
}
