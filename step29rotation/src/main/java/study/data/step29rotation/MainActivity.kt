package study.data.step29rotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val editTextTextPersonName by lazy {findViewById<EditText>(R.id.editTextTextPersonName)}
    private val textView by lazy {findViewById<TextView>(R.id.textView)}
    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            Log.d("test", "Activity가 처음 등장하였습니다.")
        } else {
            Log.d("test", "화면 회전이 발생하였습니다.")
            //2.복원한다.
            textView.text = savedInstanceState.getString("data1")
        }

        button.setOnClickListener {
            textView.text = editTextTextPersonName.text

        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //1.복원에 필요한 데이터를 저장한다.
        outState.putString("data1", textView.text.toString())

    }
}