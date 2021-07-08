package study.data.step09appbarlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {

    private val toolbar by lazy {findViewById<Toolbar>(R.id.toolbar)}

    private val imageView by lazy {findViewById<ImageView>(R.id.imageView)}

    private val toolbar_layout by lazy {findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        toolbar_layout.setCollapsedTitleTextColor(Color.WHITE)
        toolbar_layout.setExpandedTitleColor(Color.GREEN)

        toolbar_layout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL
        toolbar_layout.expandedTitleGravity = Gravity.RIGHT + Gravity.TOP

        imageView.setImageResource(R.drawable.img_android)

        supportActionBar?.title = "타이틀 입니다."
    }
}