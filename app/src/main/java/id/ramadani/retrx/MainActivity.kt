package id.ramadani.retrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvQuakes: RecyclerView

    private lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvQuakes = findViewById(R.id.rv_quakes) as RecyclerView
        val quakeAdapter = QuakeAdapter(arrayListOf())
        rvQuakes.adapter = quakeAdapter
        rvQuakes.layoutManager = LinearLayoutManager(this)

        mCompositeDisposable = CompositeDisposable()

        val getEarthQuakes = ApiBuilder.call()?.earthQuakes(minmag = 5.toDouble(), limit = 1000)

        mCompositeDisposable.add(getEarthQuakes!!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val collection = it.features
                    val data = arrayListOf<EarthQuake>()
                    collection.forEach {
                        data.add(it.properties)
                    }
                    quakeAdapter.replaceData(data)

                    Log.i("mCompositeDisposable", "onNext")
                }, {
                    Log.e("mCompositeDisposable", "onError", it)
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }
}
