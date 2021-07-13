package study.data.step35gps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private val textView by lazy {findViewById<TextView>(R.id.textView)}
    private val textView2 by lazy {findViewById<TextView>(R.id.textView2)}
    private val textView3 by lazy {findViewById<TextView>(R.id.textView3)}

    private val button by lazy {findViewById<Button>(R.id.button)}
    private val button2 by lazy {findViewById<Button>(R.id.button2)}

    val permission_list = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermissions(permission_list, 0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (r1 in grantResults){
            if(r1 == PackageManager.PERMISSION_DENIED){
                return
            }
        }

        //위치 정보를 관리하는 매니저를 추출한다.
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager

        //저장되어 있는 위치 정보값을 가져온다.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return
        }
        val location1 = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val location2 = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        if(location1 != null){
            showInfo(location1)
        }else if(location2 != null){
            showInfo(location2)
        }

        val listener = LocationListener{
            showInfo(it)
        }

        button.setOnClickListener {
            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
            }
            if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true){
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, listener)
            }
        }

        button2.setOnClickListener {
            manager.removeUpdates(listener)
        }
    }

    fun showInfo(location: Location){
        if(location != null){
            textView.text = "Provider : ${location.provider}"
            textView2.text = "위도 : ${location.latitude}"
            textView3.text = "경도 : ${location.longitude}"
        }
    }
}