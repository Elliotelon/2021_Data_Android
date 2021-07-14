package study.data.step39httpnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            thread {
                //접속할 주소
                val site = "http://192.168.219.1:8080/basic.jsp"
                val url = URL(site)

                //접속
                val conn = url.openConnection() as HttpURLConnection
                val isr = InputStreamReader(conn.inputStream, "UTF-8")
                val br = BufferedReader(isr)

                var str:String? = null
                val buf = StringBuffer()
                do{
                    str = br.readLine()
                    if(str != null){
                        buf.append("$str\n")
                    }
                }while (str != null)

                runOnUiThread {
                    textView.text = buf.toString()
                }
            }
        }
    }
}