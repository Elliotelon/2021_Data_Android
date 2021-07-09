package study.data.step16rawdata

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    var mp:MediaPlayer? = null

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}
    private val button4 by lazy {findViewById<Button>(R.id.button4)}
    private val button5 by lazy {findViewById<Button>(R.id.button5)}

    private val videoView by lazy {findViewById<VideoView>(R.id.videoView)}

    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val inputStream = resources.openRawResource(R.raw.data1)
            val isr = InputStreamReader(inputStream, "UTF-8")
            val br = BufferedReader(isr)

            var str:String? = null
            val sb = StringBuffer()

            do{
                str = br.readLine()

                if(str != null){
                    sb.append("$str\n")
                }
            }while(str != null)

            br.close()

            textView.text = sb.toString()
        }

        button2.setOnClickListener {
            if(mp == null){
                mp = MediaPlayer.create(this, R.raw.song)
                mp?.start()
            }
        }

        button3.setOnClickListener {
            if(mp != null){
                mp?.stop()
                mp = null
            }
        }

        button4.setOnClickListener {
            if(videoView.isPlaying == false){
                //영상 파일의 경로
                val uri = Uri.parse("android.resource://$packageName/raw/video")
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }
        button5.setOnClickListener {
            if(videoView.isPlaying == true){
                videoView.stopPlayback()
            }
        }
    }
}