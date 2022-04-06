package com.pra.e2loogypractical.data.model.request


import com.google.gson.annotations.SerializedName

data class MerchantRequest(
    @SerializedName("CompanyID")
    var companyID: Int,
    @SerializedName("ImageSize")
    var imageSize: String,
    @SerializedName("MenuID")
    var menuID: Int,
    @SerializedName("SearchMerchant")
    var searchMerchant: String,
    @SerializedName("SubCategoryID")
    var subCategoryID: Int
)