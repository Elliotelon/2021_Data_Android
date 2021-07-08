package study.data.step05toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val toolbar by lazy {findViewById<Toolbar>(R.id.toolbar)}

    private val textView by lazy {findViewById<TextView>(R.id.textView)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar를 액션바 대신 사용하도록 설정한다.
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.item1 -> {
                textView.text = "메뉴1을 눌렀습니다."
            }
            R.id.item2 -> {
                textView.text = "메뉴2를 눌렀습니다."
            }
            R.id.item3 -> {
                textView.text = "메뉴3을 눌렀습니다."
            }
        }

        return super.onOptionsItemSelected(item)
    }
}