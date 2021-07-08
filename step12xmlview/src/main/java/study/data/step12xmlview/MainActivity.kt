package study.data.step12xmlview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        //View 객체를 생성한다.
        val sub1 = layoutInflater.inflate(R.layout.layout_sub1, null)
        val sub2 = layoutInflater.inflate(R.layout.layout_sub2, null)
        val sub3 = layoutInflater.inflate(R.layout.layout_sub3, container)

        sub1.run{
            this.findViewById<Button>(R.id.sub1_btn).setOnClickListener {
                this.findViewById<TextView>(R.id.sub1_text).text = "sub1의 버튼을 눌렀습니다."
                textView.text = "sub1의 버튼을 눌렀습니다."
            }
        }

        button.setOnClickListener {
            container.addView(sub1)
            container.addView(sub2)
        }

        button2.setOnClickListener {
            container.removeView(sub1)
            container.removeView(sub2)
        }
    }
}