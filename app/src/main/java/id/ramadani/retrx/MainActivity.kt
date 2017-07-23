package id.ramadani.retrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvQuakes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvQuakes = findViewById(R.id.rv_quakes) as RecyclerView
        val quakeAdapter = QuakeAdapter(arrayListOf())
        rvQuakes.adapter = quakeAdapter
        rvQuakes.layoutManager = LinearLayoutManager(this)

        val api = ApiBuilder.call()

        api?.earthQuakes("geojson", "earthquake", "time", 5.toDouble(), 25)!!
                .enqueue(object : Callback<EarthQuakeCollection> {

            override fun onResponse(call: Call<EarthQuakeCollection>?, response: Response<EarthQuakeCollection>?) {
                val collection = response?.body()
                val earthQuakes = arrayListOf<EarthQuake>()
                collection?.features?.forEach {
                    earthQuakes.add(it.properties)
                }

                quakeAdapter.replaceData(earthQuakes)
                Log.d("MainActivity", "onResponse")
            }

            override fun onFailure(call: Call<EarthQuakeCollection>?, t: Throwable?) {
                Log.e("MainActivity", "onFailure", t)
            }
        })
    }
}
