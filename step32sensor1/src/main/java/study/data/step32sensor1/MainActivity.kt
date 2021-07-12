package study.data.step32sensor1

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val textView by lazy {findViewById<TextView>(R.id.textView)}
    private val textView2 by lazy {findViewById<TextView>(R.id.textView2)}
    private val textView3 by lazy {findViewById<TextView>(R.id.textView3)}

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}
    private val button3 by lazy {findViewById<Button>(R.id.button3)}
    private val button4 by lazy {findViewById<Button>(R.id.button4)}
    private val button5 by lazy {findViewById<Button>(R.id.button5)}
    private val button6 by lazy {findViewById<Button>(R.id.button6)}
    private val button7 by lazy {findViewById<Button>(R.id.button7)}
    private val button8 by lazy {findViewById<Button>(R.id.button8)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //단말기의 센서들을 관리하는 객체를 추출한다.
        val manager = getSystemService(SENSOR_SERVICE) as SensorManager

        //센서에서 값을 가져오면 반응하는 리스너
        val listener = object : SensorEventListener{

            //센서로부터 측정된 값이 변경되었을때 호출
            override fun onSensorChanged(event: SensorEvent?) {
                //센서 타입에 따라 분기
                when(event?.sensor?.type){
                    Sensor.TYPE_LIGHT -> {
                        textView.text = "주변 밝기 : ${event?.values[0]} lux"
                    }
                    Sensor.TYPE_PRESSURE -> {
                        textView.text = "현재 기압 : ${event?.values[0]} millibar"
                    }
                    Sensor.TYPE_PROXIMITY -> {
                        textView.text = "물체와의거리 : ${event?.values[0]} cm"
                    }
                    Sensor.TYPE_GYROSCOPE -> {
                        textView.text = "x축 각속도 : ${event?.values[0]}"
                        textView2.text = "y축 각속도 : ${event?.values[1]}"
                        textView3.text = "z축 각속도 : ${event?.values[2]}"
                    }
                }
            }

            //센서의 감도가 변경되었을때 호출
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }

        button.setOnClickListener {
            //조도 센서 객체를 얻어온다.
            val sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT)

            //조도 센서와 리스너를 연결한다.
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "조도 센서를 지원하지 않습니다."
            }
        }

        button2.setOnClickListener {
            manager.unregisterListener(listener)
        }

        button3.setOnClickListener {
            val sensor = manager.getDefaultSensor(Sensor.TYPE_PRESSURE)
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "기압 센서를 지원하지 않습니다."
            }
        }
        button4.setOnClickListener {
            manager.unregisterListener(listener)
        }

        button5.setOnClickListener {
            val sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "근접센서를 지원하지 않습니다."
            }
        }
        button6.setOnClickListener {
            manager.unregisterListener(listener)
        }
        button7.setOnClickListener {
            val sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "자이로 스코프 센서를 지원하지 않습니다."
            }
        }
        button8.setOnClickListener {
            manager.unregisterListener(listener)
        }
    }
}