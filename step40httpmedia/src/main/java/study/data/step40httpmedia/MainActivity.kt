package study.data.step40httpmedia

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var mp:MediaPlayer? = null

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}
    private val button4 by lazy {findViewById<Button>(R.id.button4)}
    private val button5 by lazy {findViewById<Button>(R.id.button5)}

    private val imageView by lazy {findViewById<ImageView>(R.id.imageView)}

    private val videoView by lazy {findViewById<VideoView>(R.id.videoView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val url = URL("http://192.168.219.1:8080/img_android.jpg")

            val conn = url.openConnection() as HttpURLConnection

            val bitmap = BitmapFactory.decodeStream(conn.inputStream)

            runOnUiThread {
                imageView.setImageBitmap(bitmap)
            }
        }
        button2.setOnClickListener {
            if(mp == null){
                val uri = Uri.parse("http://192.168.219.1:8080/song.mp3")
                mp = MediaPlayer.create(this, uri)
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
                val uri = Uri.parse("http://192.168.219.1:8080/video.mp4")
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }

        button5.setOnClickListener {
            if(videoView.isPlaying == true)
            videoView.stopPlayback()
        }
    }
}