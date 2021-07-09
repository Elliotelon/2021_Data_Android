package study.data.step20cpapp2

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}
    private val button4 by lazy {findViewById<Button>(R.id.button4)}

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            //Content Provider의 이름을 가지고 있는 uri 객체를 생성한다.
            val uri = Uri.parse("content://study.data.dbprovider")

            //첫번째 : 접속할 프로바이더 uri
            //두번째 : 가져올 컬럼 목록배열 null이면 모두 가져온다.
            //세번째 : 조건절
            //네번째 : 조건절의 ?에 바인딩 될 값 배열
            //다섯번째 : 정렬 기준
            val c1 = contentResolver.query(uri, null, null, null, null)

            textView.text = ""

            while (c1?.moveToNext()!!) {

                val idx1 = c1?.getColumnIndex("idx")
                val idx2 = c1?.getColumnIndex("textData")
                val idx3 = c1?.getColumnIndex("intData")
                val idx4 = c1?.getColumnIndex("floatData")
                val idx5 = c1?.getColumnIndex("dateData")

                val idx = c1?.getInt(idx1)
                val textData = c1?.getString(idx2)
                val intData = c1?.getInt(idx3)
                val floatData = c1?.getDouble(idx4)
                val dateData = c1?.getString(idx5)

                textView.append(" idx : $idx\n")
                textView.append(" textData : $textData\n")
                textView.append(" intData : $intData\n")
                textView.append(" floatData : $floatData\n")
                textView.append(" dateData : $dateData\n\n")
            }
        }

        button2.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val now = sdf.format(Date())

            val cv1 = ContentValues()
            cv1.put("textData", "문자열3")
            cv1.put("intData", 300)
            cv1.put("floatData", 33.33)
            cv1.put("dateData", now)

            val cv2 = ContentValues()
            cv2.put("textData", "문자열4")
            cv2.put("intData", 400)
            cv2.put("floatData", 44.44)
            cv2.put("dateData", now)

            val uri = Uri.parse("content://study.data.dbprovider")

            contentResolver.insert(uri, cv1)
            contentResolver.insert(uri, cv2)

            textView.text = "저장완료"
        }
        button3.setOnClickListener {
            val cv = ContentValues()

            cv.put("textData", "문자열100")
            val where = "idx = ?"
            val args = arrayOf("1")

            val uri = Uri.parse("content://study.data.dbprovider")

            contentResolver.update(uri, cv, where, args)

            textView.text = "수정완료"
        }
        button4.setOnClickListener {

            val where = "idx = ? "
            val args = arrayOf("1")

            val uri = Uri.parse("content://study.data.dbprovider")

            contentResolver.delete(uri, where, args)

            textView.text = "삭제완료"
        }
    }
}