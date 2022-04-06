package com.pra.e2loogypractical.data.model.response


import com.google.gson.annotations.SerializedName

data class MerchantApiResponse(
    @SerializedName("Result")
    var result: List<Result>,
)