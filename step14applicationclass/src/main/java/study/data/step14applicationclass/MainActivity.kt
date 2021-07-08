package study.data.step14applicationclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    private val button by lazy {findViewById<Button>(R.id.button)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val app = application as AppClass
            app.method1()
            app.value1 = 100
            app.value2 = "안녕하세요."

            val secondIntent = Intent(this, SecondActivity::class.java)
            startActivityForResult(secondIntent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val app = application as AppClass
        textView.text = "value1 : ${app.value1}\n"
        textView.append("value2 : ${app.value2}")
        super.onActivityResult(requestCode, resultCode, data)
    }
}