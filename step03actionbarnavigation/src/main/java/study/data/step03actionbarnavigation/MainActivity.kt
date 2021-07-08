package study.data.step03actionbarnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView2 by lazy {findViewById<TextView>(R.id.textView2)}

    private val button by lazy {findViewById<Button>(R.id.button)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val secondIntent = Intent(this, SecondActivity::class.java)
            startActivity(secondIntent)

        }
    }
}