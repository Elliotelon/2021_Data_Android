package study.data.step25imageanimation

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    private val imageView by lazy {findViewById<ImageView>(R.id.imageView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //imageView.setImageResource(R.drawable.ani_data)
        //val drawable = getDrawable(R.drawable.ani_data)
        //imageView.setImageDrawable(drawable)

        //애니메이션 객체를 추출한다.
        val ani = imageView.drawable as AnimationDrawable

        button.setOnClickListener {
            ani.start()
        }

        button2.setOnClickListener {
            ani.stop()
        }
    }
}