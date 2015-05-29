package com.indibase.conconi.provider;

/**
 * Created by Ralph on 5/29/2015.
 */
public class Match {

    public DatabaseSchema schema;
    public String uri;
    public String tableName;

    public Match(DatabaseSchema schema, String uri, String tableName) {
        this.schema = schema;
        this.uri = uri;
        this.tableName = tableName;
    }
}
