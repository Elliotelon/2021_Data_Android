package study.data.step01actionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.item1 -> {
                textView.text = "첫번째 메뉴"
            }
            R.id.item2 -> {
                textView.text = "두번째 메뉴"
            }
            R.id.item3 -> {
                textView.text = "세번째 메뉴"
            }
            R.id.item4 -> {
                textView.text = "네번째 메뉴"
            }
        }

        return super.onOptionsItemSelected(item)
    }
}