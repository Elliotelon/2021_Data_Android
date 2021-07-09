package study.data.step22preferencesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager

class ResultFragment : Fragment() {
    lateinit var textView2 : TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_result, null)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView2 = view.findViewById<TextView>(R.id.textView2)

        val pref = PreferenceManager.getDefaultSharedPreferences(context)

        val data1 = pref.getString("data1", null)
        textView2.text = "data1 : $data1\n"

        val data2 = pref.getBoolean("data2", false)
        textView2.append("data2 : $data2\n")

        val data3 = pref.getBoolean("data3", false)
        textView2.append("data3 : $data3\n")

        val data4 = pref.getString("data4", null)
        textView2.append("data4 : $data4\n")

        val data5 = pref.getStringSet("data5", null)
        for(str in data5!!){
            textView2.append("data5 : $str\n")
        }
    }
}