package study.data.step17assets

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val inputStream = assets.open("text/data1.txt")
            val isr = InputStreamReader(inputStream, "UTF-8")
            val br = BufferedReader(isr)

            var str:String? = null
            val sb = StringBuffer()

            do{
                str = br.readLine()
                if(str != null){
                    sb.append("$str\n")
                }
            }while (str != null)
            br.close()

            textView.text = sb.toString()
        }
        button2.setOnClickListener {
            val inputStream = assets.open("text/data2.txt")
            val isr = InputStreamReader(inputStream, "UTF-8")
            val br = BufferedReader(isr)

            var str:String? = null
            val sb = StringBuffer()

            do{
                str = br.readLine()
                if(str != null){
                    sb.append("$str\n")
                }
            }while (str != null)
            br.close()

            textView.text = sb.toString()
        }

        button3.setOnClickListener {
            //폰트 객체를 만든다.
            val face = Typeface.createFromAsset(assets, "font/NotoSerifKR-Regular.otf")
            textView.typeface = face
        }
    }
}