package study.data.step13codeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    private val container by lazy {findViewById<LinearLayout>(R.id.container)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val param1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val btn1 = Button(this)
        btn1.text = "추가된 버튼입니다."
        btn1.layoutParams = param1
        btn1.setOnClickListener {
            textView.text = "추가된 버튼을 눌렀습니다."
        }

        val img1 = ImageView(this)
        img1.layoutParams = param1
        img1.setImageResource(R.mipmap.ic_launcher)

        button.setOnClickListener {
            container.addView(btn1)
            container.addView(img1)
        }

        button2.setOnClickListener {
            container.removeView(btn1)
            container.removeView(img1)
        }
    }
}