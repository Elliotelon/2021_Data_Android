package study.data.step38socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            Log.d("test", "111")
            thread {
                //서버에 접속한다.
                val socket = Socket("192.168.219.1", 55555)

                Log.d("test", "$socket")

                //서버로 부터 데이터를 수신한다.
                val inputStream = socket.getInputStream()
                val dis = DataInputStream(inputStream)

                val a1 = dis.readInt()
                val a2 = dis.readDouble()
                val a3 = dis.readBoolean()
                val a4 = dis.readUTF()

                textView.text = "a1 : $a1\n"
                textView.append("a2 : $a2\n")
                textView.append("a3 : $a3\n")
                textView.append("a4 : $a4\n")

                //서버로 데이터를 보낸다.
                val outPutStream = socket.getOutputStream()
                val dos = DataOutputStream(outPutStream)

                dos.writeInt(200)
                dos.writeDouble(22.22)
                dos.writeBoolean(false)
                dos.writeUTF("클라이언트가 보낸 문자열")

                socket.close()

            }
        }

    }

}