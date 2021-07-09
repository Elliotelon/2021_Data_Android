package study.data.step20cpapp1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper : SQLiteOpenHelper {

    constructor(context: Context) :super(context, "Test.db", null, 1)

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """
            create table TestTable
             (idx integer primary key autoincrement,
              textData text not null,
              intData integer not null,
              floatData real not null,
              dateData date not null)
        """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}