package study.data.step30deviceinfo

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}
    private val textView by lazy {findViewById<TextView>(R.id.textView)}

    val permission_list = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.READ_SMS,
        Manifest.permission.READ_PHONE_NUMBERS
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permission_list, 0)

        button.setOnClickListener {
            //TelephonyManager를 추출한다.
            val manager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_SMS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                textView.text = "권한을 허용해 주세요"
            }else{
                textView.text = "전화번호 : ${manager.line1Number}\n"
                textView.append("SIM 국가코드 : ${manager.simCountryIso}\n")
                textView.append("모바일 국가코드 + 모바일 네트워크 코드 : ${manager.simOperator}\n")
                textView.append("서비스 이름 : ${manager.simOperatorName}\n")
                textView.append("SIM 상태(통신 가능여부, PIN LOCK 여부) : ${manager.simState}\n")
                textView.append("음성 메일 번호 : ${manager.voiceMailNumber}")
            }
        }

        button2.setOnClickListener {
            textView.text = "보드이름 : ${Build.BOARD}\n"
            textView.append("소프트웨어를 커스터마이징한 회사 : ${Build.BRAND}\n")
            textView.append("제조사 디자인 명 : ${Build.DEVICE}\n")
            textView.append("사용자에게 표시되는 빌드 ID : ${Build.DISPLAY}\n")
            textView.append("빌드 고유 ID : ${Build.FINGERPRINT}\n")
            textView.append("ChangeList 번호 : ${Build.ID}\n")
            textView.append("제품/하드웨어 제조업체 : ${Build.MANUFACTURER}\n")
            textView.append("제품 모델명 : ${Build.MODEL}\n")
            textView.append("제품명 : ${Build.PRODUCT}\n")
            textView.append("빌드 구분 : ${Build.TAGS}\n")
            textView.append("빌드 타입 : ${Build.TYPE}\n")
            textView.append("안드로이드 버전 : ${Build.VERSION.RELEASE}\n")
        }

        button3.setOnClickListener {
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                //안드로이드 11버전 부터
                val matrix = wm.currentWindowMetrics
                val width = matrix.bounds.width()
                val height = matrix.bounds.height()

                textView.text = "width : $width\n"
                textView.append("height : $height\n")
            }else {
                //안드로이드 10버전까지
                val display = wm.defaultDisplay

                val point = Point()
                display.getSize(point)

                textView.text = "width : ${point.x}\n"
                textView.append("height : ${point.y}\n")
            }
        }
    }
}