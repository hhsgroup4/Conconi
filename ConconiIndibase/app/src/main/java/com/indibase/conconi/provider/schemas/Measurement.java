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
        return "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 0, 110);"+
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 10, 110)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 20, 110)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 30, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 40, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 50, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 60, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 70, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 80, 111)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 90, 112)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 100, 112)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 110, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 120, 114)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 130, 114)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 140, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 150, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 160, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 170, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 180, 113)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 190, 114)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 200, 114)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 210, 115)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 220, 116)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 230, 116)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 240, 117)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 250, 118)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 260, 119)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 270, 120)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 280, 121)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 290, 123)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 300, 124)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 310, 123)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 320, 123)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 330, 122)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 340, 121)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 350, 120)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 360, 119)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 370, 121)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 380, 123)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 390, 125)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 400, 126)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 410, 129)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 420, 131)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 430, 132)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 440, 132)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 450, 133)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 460, 134)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 470, 135)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 480, 136)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 490, 137)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 500, 138)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 510, 140)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 520, 141)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 530, 143)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 540, 145)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 550, 147)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 560, 149)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 570, 151)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 580, 152)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 590, 154)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 600, 156)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 610, 157)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 620, 158)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 630, 160)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 640, 161)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 650, 162)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 660, 163)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 670, 164)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 680, 165)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 690, 166)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 700, 167)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 710, 168)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 720, 170)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 730, 170)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 740, 171)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 750, 172)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 760, 172)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 770, 173)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 780, 174)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 790, 174)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 800, 175)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 810, 176)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 820, 176)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 830, 177)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 840, 177)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 850, 177)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 860, 178)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 870, 179)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 880, 179)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 890, 180)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 900, 181)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 910, 182)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 920, 182)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 930, 183)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 940, 184)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 950, 185)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 960, 186)" +
        "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) VALUES (1, 964, 186)";
    }
}
