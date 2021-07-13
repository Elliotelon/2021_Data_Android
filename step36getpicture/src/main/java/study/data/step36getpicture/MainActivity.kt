package study.data.step36getpicture

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
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

                    val degree = getDegree(contentUri, contentUri.path!!)

                    val bitmap2 = resizeBitmap(1024, bitmap)
                    val bitmap3 = rotateBitmap(bitmap2, degree)

                    imageView.setImageBitmap(bitmap3)

                    //사진 파일 삭제한다.
                    val file = File(contentUri.path)
                    file.delete()
                }
           }
        }
    }
    //사진의 사이즈를 조정하는 메소드
    fun resizeBitmap(targetWidth:Int, source:Bitmap):Bitmap{
        //이미지의 비율을 계산한다.
        val ratio = targetWidth.toDouble()/source.width.toDouble()
        //보정될 세로 길이를 구한다.
        val targetHeight = (source.height*ratio).toInt()
        //크기를 조정한 bitmap객체를 생성한다.
        val result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
        return result
    }

    //이미지의 회전 각도값을 구한다.
    fun getDegree(uri:Uri, source:String):Float{

        var exif:ExifInterface? = null

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val photoUri = MediaStore.setRequireOriginal(uri)
            val stream = contentResolver.openInputStream(photoUri)
            exif = ExifInterface(stream!!)
        } else {
            exif = ExifInterface(source)
        }

        var degree = 0
        val ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1)
        when(ori){
            ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
            ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
            ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
        }
        return degree.toFloat()
    }

    fun rotateBitmap(bitmap:Bitmap, degree:Float):Bitmap{
        //각도값을 관리하는 객체
        val matrix = Matrix()
        matrix.postRotate(degree)
        //회전된 이미지를 받아온다.
        val bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, false)
        return bitmap2
    }
}