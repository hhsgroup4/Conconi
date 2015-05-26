package com.indibase.conconi.provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Ralph on 5/20/2015.
 */
public class ContentProvider extends android.content.ContentProvider
{
    public static final String AUTHORITY = "com.indibase.provider.conconi";
    public UriMatcher uriMatcher;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private static final int URI_PREFIX = 1000; //increase this if more than 1000 schema's exist

    private int getSchemeId(String tableName){
        for(DatabaseSchema schema : dbHelper.schemas)
            if(schema.getTableName().equals(tableName)){
                return dbHelper.schemas.indexOf(schema);
            }
        return 0;
    }

    private String getTableName(Uri uri){
        int match = uriMatcher.match(uri);
        for(DatabaseSchema schema: dbHelper.schemas)
            if(match == getSchemeId(schema.getTableName()))
                return schema.getTableName();
            else if(match == getSchemeId(schema.getTableName()) + URI_PREFIX)
                return schema.getTableName();

        return null;
    }

    private void initUriMatcher(){
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        int index = 0;
        for(DatabaseSchema schema: dbHelper.schemas) {
            uriMatcher.addURI(AUTHORITY, schema.getTableName(), index);
            if(schema.canModifySingleRow())
                uriMatcher.addURI(AUTHORITY, schema.getTableName() + "/#", index + URI_PREFIX);
            index++;
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        initUriMatcher();
        return (dbHelper != null) ? true : false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query( getTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);

        //---register to watch a content URI for changes---
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        for(DatabaseSchema schema: dbHelper.schemas)
            if(match == getSchemeId(schema.getTableName()))
                return "vnd.android.cursor.dir/vnd.indibase.conconi." + schema.getTableName();
            else if(match == getSchemeId(schema.getTableName()) + URI_PREFIX)
                return "vnd.android.cursor.item/vnd.indibase.conconi." + schema.getTableName();

        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(getTableName(uri), "", values);
        if(rowID > 0){
            Uri _uri = ContentUris.withAppendedId(uri, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int count = db.delete(getTableName(uri), selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int count = db.update(getTableName(uri), values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}