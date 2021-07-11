package study.data.step24imageres

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private val imageView2 by lazy {findViewById<ImageView>(R.id.imageView2)}
    private val imageView3 by lazy {findViewById<ImageView>(R.id.imageView3)}
    private val imageView4 by lazy {findViewById<ImageView>(R.id.imageView4)}

    private val container by lazy {findViewById<LinearLayout>(R.id.container)}

    private val button by lazy {findViewById<Button>(R.id.button)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView2.setImageResource(R.drawable.img_android)

        //Bitmap : JPG, PNG, GIF 파일로 부터 읽어온 이미지 데이터를 관리
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_android)
        imageView3.setImageBitmap(bitmap)

        //Drawable : Bitmap을 포함한 다양한 타입으로 부터 이미지 데이터를 관리
        val drawable = getDrawable(R.drawable.img_android)
        imageView4.setImageDrawable(drawable)

        //배경 타일 이미지를 생성한다.
        //container.setBackgroundResource(R.drawable.tile)

        val drawable2 = getDrawable(R.drawable.tile)
        container.background = drawable2

        // layer 이미지를 사용한다.
        val drawable3 = getDrawable(R.drawable.layer)
        imageView4.setImageDrawable(drawable3)

        //상태 이미지를 사용한다.
        val darwable4 = getDrawable(R.drawable.btn_image)
        button.background = darwable4

    }
}