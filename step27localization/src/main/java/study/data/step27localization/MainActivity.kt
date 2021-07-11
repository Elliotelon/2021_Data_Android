package study.data.step27localization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val textView2 by lazy {findViewById<TextView>(R.id.textView2)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val str2 = getString(R.string.str1)
        textView2.text = str2
    }
}