package id.ramadani.retrx

import retrofit2.Retrofit
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
            val client = Retrofit.Builder()
                    .baseUrl(API_URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return client
        }
    }
}