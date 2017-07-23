package id.ramadani.retrx

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by dani on 7/23/17.
 */
class ApiBuilder {

    companion object {
        val API_URL_BASE = "https://earthquake.usgs.gov"

        fun call(): UsgsService? {
            return getClient()?.create(UsgsService::class.java)
        }

        private fun getClient(): Retrofit? {
            val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

            val client = Retrofit.Builder()
                    .baseUrl(API_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter)
                    .build()

            return client
        }
    }
}