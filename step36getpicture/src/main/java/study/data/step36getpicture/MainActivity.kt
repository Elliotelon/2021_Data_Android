package study.data.step36getpicture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {

    lateinit var contentUri : Uri

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val imageView by lazy {findViewById<ImageView>(R.id.imageView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val file_path = getExternalFilesDir(null).toString()

        button.setOnClickListener {
            val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent1, 1)
        }

        button2.setOnClickListener {
            val intent2 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            //촬영한 사진이 저장될 파일의 이름
            val file_name = "/temp_${System.currentTimeMillis()}.jpg"
            //경로 + 파일이름
            val pic_path = "$file_path/$file_name"

            val file = File(pic_path)

            //사진이 저장될 위치를 관리하는 uri 객체
            contentUri = FileProvider.getUriForFile(this, "study.data.camera.file_provider", file)

            if(contentUri != null){
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                startActivityForResult(intent2, 2)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1 -> {
                if(resultCode == RESULT_OK){
                    val bitmap = data?.getParcelableExtra<Bitmap>("data")
                    imageView.setImageBitmap(bitmap)
                }
            }

            2 -> {
                if(resultCode == RESULT_OK){
                    val bitmap = BitmapFactory.decodeFile(contentUri.path)
                    imageView.setImageBitmap(bitmap)

                    //사진 파일 삭제한다.
                    val file = File(contentUri.path)
                    file.delete()
                }
           }
        }
    }
}