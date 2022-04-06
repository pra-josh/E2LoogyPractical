package com.pra.myapplication.data.model.api

import com.pra.e2loogypractical.data.model.response.MerchantApiResponse
import retrofit2.Call
import retrofit2.http.*

interface WebApi {

    @POST("merchant")
    @FormUrlEncoded
    fun getMerchantList(@FieldMap params:Map<String,String>): Call<MerchantApiResponse>
}