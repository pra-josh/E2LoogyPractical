package com.pra.e2loogypractical.domain

import android.content.Context
import com.pra.e2loogypractical.data.model.response.MerchantApiResponse
import com.pra.interview.data.api.WebApiClient
import com.pra.myapplication.data.model.api.WebApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APPRepository(private val app: Context) : WebApi {

    override fun getMerchantList(params: Map<String, String>): Call<MerchantApiResponse> {
        val mResponseCall: Call<MerchantApiResponse> = WebApiClient(app).getMerchantList(params)
        return mResponseCall
    }
}