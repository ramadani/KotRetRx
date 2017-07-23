package id.ramadani.retrx

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dani on 7/23/17.
 */
interface UsgsService {

    @GET("fdsnws/event/1/query")
    fun earthQuakes(@Query("format") format: String = "geojson",
                    @Query("eventtype") eventtype: String = "earthquake",
                    @Query("orderby") orderby: String = "time",
                    @Query("minmag") minmag: Double,
                    @Query("limit") limit: Int): Observable<EarthQuakeCollection>
}