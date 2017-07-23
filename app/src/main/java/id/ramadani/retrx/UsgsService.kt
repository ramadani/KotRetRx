package id.ramadani.retrx

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dani on 7/23/17.
 */
interface UsgsService {

    @GET("fdsnws/event/1/query")
    fun earthQuakes(@Query("format") format: String, @Query("eventtype") eventtype: String,
                    @Query("orderby") orderby: String, @Query("minmag") minmag: Double,
                    @Query("limit") limit: Int): Call<EarthQuakeCollection>
}