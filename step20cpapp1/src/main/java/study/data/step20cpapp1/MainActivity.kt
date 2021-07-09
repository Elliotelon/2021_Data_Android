package study.data.step20cpapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val helper = DBHelper(this)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val now = sdf.format(Date())

            val sql = """
                insert into TestTable
                (textData, intData, floatData, dateData)
                values(?,?,?,?)
            """.trimIndent()

            val arg1 = arrayOf("문자열1", "100", "11.11", now)
            val arg2 = arrayOf("문자열2", "200", "22.22", now)

            helper.writableDatabase.execSQL(sql, arg1)
            helper.writableDatabase.execSQL(sql, arg2)

            helper.writableDatabase.close()

            textView.text = "저장완료"
        }

        button2.setOnClickListener {
            val helper = DBHelper(this)

            val sql = "select * from TestTable"

            val c1 = helper.writableDatabase.rawQuery(sql, null)

            textView.text = ""
            
            while (c1.moveToNext()){
                val idx1 = c1.getColumnIndex("idx")
                val idx2 = c1.getColumnIndex("textData")
                val idx3 = c1.getColumnIndex("intData")
                val idx4 = c1.getColumnIndex("floatData")
                val idx5 = c1.getColumnIndex("dateData")

                val idx = c1.getInt(idx1)
                val textData = c1.getString(idx2)
                val intData = c1.getInt(idx3)
                val floatData = c1.getDouble(idx4)
                val dateData = c1.getString(idx5)

                textView.append(" idx : $idx\n")
                textView.append(" textData : $textData\n")
                textView.append(" intData : $intData\n")
                textView.append(" floatData : $floatData\n")
                textView.append(" dateData : $dateData\n\n")
            }
            helper.writableDatabase.close()
        }

    }
}