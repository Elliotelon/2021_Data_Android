package study.data.step08viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : FragmentActivity() {

    private val toolbar by lazy {findViewById<android.widget.Toolbar>(R.id.toolbar)}
    private val pager2 by lazy {findViewById<ViewPager2>(R.id.pager2)}

    val frag1 = SubFragment1()
    val frag2 = SubFragment2()
    val frag3 = SubFragment3()

    val fragList = arrayOf(
            frag1, frag2, frag3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setActionBar(toolbar)

        val adapter1 = object : FragmentStateAdapter(this){

            override fun getItemCount(): Int {
                return fragList.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragList[position]
            }
        }
        pager2.adapter = adapter1
    }
}