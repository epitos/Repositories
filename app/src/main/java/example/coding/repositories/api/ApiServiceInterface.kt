package example.coding.repositories.api

import example.coding.repositories.model.Repository
import example.coding.repositories.util.Constants
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET(Constants.GET_REPOSITORIES_ENDPOINT)
    fun getRepositories(): Observable<List<Repository>>

    companion object {
        fun create(): ApiServiceInterface {
            val requestInterface = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return requestInterface.create(ApiServiceInterface::class.java)
        }
    }
}