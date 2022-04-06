package com.pra.interview.data.api

import android.content.Context
import com.pra.myapplication.data.model.api.WebApi
import java.lang.Exception
import com.google.gson.GsonBuilder
import com.pra.e2loogypractical.data.model.response.MerchantApiResponse
import java.io.IOException
import java.util.Collections
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.*
import okhttp3.internal.Util.immutableList
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebApiClient(private val mContext: Context) {
    private val Base_URL = "https://staging.sunteccity.com.sg/";
    val API_PATH_POST = "ver16/index.php/apitokenv8/list/model/"

    //ver16/index.php/apitokenv8/list/model/merchant

    private var webApi: WebApi? = null
    var webApiClient: WebApiClient? = null


    fun getWebApiWithToken(): WebApi? {
        return try {
            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(mContext.cacheDir, cacheSize)
            System.setProperty("http.keepAlive", "false")

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // for display request and response parameter
            val httpClient = OkHttpClient.Builder()
            httpClient.protocols(immutableList(Protocol.HTTP_1_1))
            httpClient.connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
            val interceptor = Interceptor { chain ->
                var request = chain.request()
                val url = request.url().newBuilder().build()
                request = request.newBuilder().url(url)
                    .addHeader("issecuritydisable", "0")
                    .addHeader(
                        "Cookie",
                        "PHPSESSID=inlbep5k3050okffiuk7e4qc51; AWSELB=45A3A5110A3D4C17F1D7E199225D50566F3A5A1DAE057F5F8C2DB9D7900C5EDA0B0F10E085E12FEC89C248FBB0235AF67394DBFE209998814DCA968137D7EDD6454A9A2A19; AWSELBCORS=45A3A5110A3D4C17F1D7E199225D50566F3A5A1DAE057F5F8C2DB9D7900C5EDA0B0F10E085E12FEC89C248FBB0235AF67394DBFE209998814DCA968137D7EDD6454A9A2A19"
                    )
                    .build()
                chain.proceed(request)
            }
            httpClient.addInterceptor(interceptor)
            httpClient.addInterceptor(logging)
            val client = httpClient.cache(myCache).build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Base_URL + API_PATH_POST)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().serializeNulls().create()
                    )
                )
                .client(client)
                .build()
            webApi = retrofit.create(WebApi::class.java)
            webApi
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getMerchantList(params: Map<String, String>): Call<MerchantApiResponse> =
        getWebApiWithToken()!!.getMerchantList(params)

}
