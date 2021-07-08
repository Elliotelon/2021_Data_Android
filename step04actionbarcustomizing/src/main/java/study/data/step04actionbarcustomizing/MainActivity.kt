package study.data.step04actionbarcustomizing

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //layout을 통해 View를 생성한다.
        val topBar = layoutInflater.inflate(R.layout.cutom_actionbar, null)
        supportActionBar?.customView = topBar

        topBar.run {
            val textView2 = findViewById<TextView>(R.id.textView2)
            val button = findViewById<Button>(R.id.button)

            textView2.text = "커스텀 액션바"
            textView2.setTextColor(Color.WHITE)

            button.setOnClickListener {
                textView.text = "액션바의 버튼을 눌렀습니다."
            }
        }

    }
}