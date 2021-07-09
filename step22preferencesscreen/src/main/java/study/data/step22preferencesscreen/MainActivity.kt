package study.data.step22preferencesscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    val settingFragment = SettingFragment()
    val resultFragment = ResultFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container, settingFragment)
            tran.commit()
        }

        button2.setOnClickListener {
            val tran = supportFragmentManager.beginTransaction()
            tran.replace(R.id.container, resultFragment)
            tran.commit()
        }

    }
}