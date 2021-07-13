package study.data.step33sensor2

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(SENSOR_SERVICE) as SensorManager
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                when(event?.sensor?.type){
                    Sensor.TYPE_ACCELEROMETER -> {
                        textView.text = "x축 기울기 : ${event?.values[0]}"
                        textView2.text = "y축 기울기 : ${event?.values[1]}"
                        textView3.text = "z축 기울기 : ${event?.values[2]}"
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        textView.text = "x축 주변 자기장 : ${event?.values[0]}"
                        textView2.text = "y축 주변 자기장 : ${event?.values[1]}"
                        textView3.text = "z축 주변 자기장 : ${event?.values[2]}"
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }
        button.setOnClickListener {
            val sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "가속도 센서를 지원하지 않습니다."
            }
        }
        button2.setOnClickListener {
            manager.unregisterListener(listener)
        }
        button3.setOnClickListener {
            val sensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            val chk = manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
            if(chk == false){
                textView.text = "자기장 센서를 지원하지 않습니다."
            }
            button4.setOnClickListener {
                manager.unregisterListener(listener)
            }
        }
    }
}