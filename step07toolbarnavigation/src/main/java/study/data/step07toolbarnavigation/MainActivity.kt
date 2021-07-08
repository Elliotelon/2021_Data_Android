package study.data.step07toolbarnavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private val textView2 by lazy {findViewById<TextView>(R.id.textView)}
    private val button by lazy {findViewById<Button>(R.id.button)}

    private val toolbar by lazy {findViewById<Toolbar>(R.id.toolbar)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        button.setOnClickListener {
            val secondIntent = Intent(this, SecondActivity::class.java)
            startActivity(secondIntent)
        }

    }
}