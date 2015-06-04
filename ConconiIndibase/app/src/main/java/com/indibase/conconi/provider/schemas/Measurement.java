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
        return "INSERT INTO `" + getTableName() + "` (`test_id`, `second`, `bpm`) " +
        "SELECT 1 AS `test_id`, 0 AS `second`, 110 AS `bpm` "+
        "UNION SELECT 1, 10, 110 " +
        "UNION SELECT 1, 20, 110 " +
        "UNION SELECT 1, 30, 111 " +
        "UNION SELECT 1, 40, 111 " +
        "UNION SELECT 1, 50, 111 " +
        "UNION SELECT 1, 60, 111 " +
        "UNION SELECT 1, 70, 111 " +
        "UNION SELECT 1, 80, 111 " +
        "UNION SELECT 1, 90, 112 " +
        "UNION SELECT 1, 100, 112 " +
        "UNION SELECT 1, 110, 113 " +
        "UNION SELECT 1, 120, 114 " +
        "UNION SELECT 1, 130, 114 " +
        "UNION SELECT 1, 140, 113 " +
        "UNION SELECT 1, 150, 113 " +
        "UNION SELECT 1, 160, 113 " +
        "UNION SELECT 1, 170, 113 " +
        "UNION SELECT 1, 180, 113 " +
        "UNION SELECT 1, 190, 114 " +
        "UNION SELECT 1, 200, 114 " +
        "UNION SELECT 1, 210, 115 " +
        "UNION SELECT 1, 220, 116 " +
        "UNION SELECT 1, 230, 116 " +
        "UNION SELECT 1, 240, 117 " +
        "UNION SELECT 1, 250, 118 " +
        "UNION SELECT 1, 260, 119 " +
        "UNION SELECT 1, 270, 120 " +
        "UNION SELECT 1, 280, 121 " +
        "UNION SELECT 1, 290, 123 " +
        "UNION SELECT 1, 300, 124 " +
        "UNION SELECT 1, 310, 123 " +
        "UNION SELECT 1, 320, 123 " +
        "UNION SELECT 1, 330, 122 " +
        "UNION SELECT 1, 340, 121 " +
        "UNION SELECT 1, 350, 120 " +
        "UNION SELECT 1, 360, 119 " +
        "UNION SELECT 1, 370, 121 " +
        "UNION SELECT 1, 380, 123 " +
        "UNION SELECT 1, 390, 125 " +
        "UNION SELECT 1, 400, 126 " +
        "UNION SELECT 1, 410, 129 " +
        "UNION SELECT 1, 420, 131 " +
        "UNION SELECT 1, 430, 132 " +
        "UNION SELECT 1, 440, 132 " +
        "UNION SELECT 1, 450, 133 " +
        "UNION SELECT 1, 460, 134 " +
        "UNION SELECT 1, 470, 135 " +
        "UNION SELECT 1, 480, 136 " +
        "UNION SELECT 1, 490, 137 " +
        "UNION SELECT 1, 500, 138 " +
        "UNION SELECT 1, 510, 140 " +
        "UNION SELECT 1, 520, 141 " +
        "UNION SELECT 1, 530, 143 " +
        "UNION SELECT 1, 540, 145 " +
        "UNION SELECT 1, 550, 147 " +
        "UNION SELECT 1, 560, 149 " +
        "UNION SELECT 1, 570, 151 " +
        "UNION SELECT 1, 580, 152 " +
        "UNION SELECT 1, 590, 154 " +
        "UNION SELECT 1, 600, 156 " +
        "UNION SELECT 1, 610, 157 " +
        "UNION SELECT 1, 620, 158 " +
        "UNION SELECT 1, 630, 160 " +
        "UNION SELECT 1, 640, 161 " +
        "UNION SELECT 1, 650, 162 " +
        "UNION SELECT 1, 660, 163 " +
        "UNION SELECT 1, 670, 164 " +
        "UNION SELECT 1, 680, 165 " +
        "UNION SELECT 1, 690, 166 " +
        "UNION SELECT 1, 700, 167 " +
        "UNION SELECT 1, 710, 168 " +
        "UNION SELECT 1, 720, 170 " +
        "UNION SELECT 1, 730, 170 " +
        "UNION SELECT 1, 740, 171 " +
        "UNION SELECT 1, 750, 172 " +
        "UNION SELECT 1, 760, 172 " +
        "UNION SELECT 1, 770, 173 " +
        "UNION SELECT 1, 780, 174 " +
        "UNION SELECT 1, 790, 174 " +
        "UNION SELECT 1, 800, 175 " +
        "UNION SELECT 1, 810, 176 " +
        "UNION SELECT 1, 820, 176 " +
        "UNION SELECT 1, 830, 177 " +
        "UNION SELECT 1, 840, 177 " +
        "UNION SELECT 1, 850, 177 " +
        "UNION SELECT 1, 860, 178 " +
        "UNION SELECT 1, 870, 179 " +
        "UNION SELECT 1, 880, 179 " +
        "UNION SELECT 1, 890, 180 " +
        "UNION SELECT 1, 900, 181 " +
        "UNION SELECT 1, 910, 182 " +
        "UNION SELECT 1, 920, 182 " +
        "UNION SELECT 1, 930, 183 " +
        "UNION SELECT 1, 940, 184 " +
        "UNION SELECT 1, 950, 185 " +
        "UNION SELECT 1, 960, 186 " +
        "UNION SELECT 1, 964, 186";
    }
}
