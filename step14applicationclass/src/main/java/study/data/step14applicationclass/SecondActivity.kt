package study.data.step14applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private val textView2 by lazy {findViewById<TextView>(R.id.textView2)}

    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val app = application as AppClass
        textView2.text = "value1 : ${app.value1}\n"
        textView2.append("value2 : ${app.value2}")

        button2.setOnClickListener {
            app.value1 = 200
            app.value2 ="반갑습니다."
            finish()
        }
    }
}