package com.pra.e2loogypractical.data.model.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("AboutStore")
    var aboutStore: String,
    @SerializedName("DetailImage")
    var detailImage: String,
    @SerializedName("Email")
    var email: String,
    @SerializedName("IsSponsored")
    var isSponsored: String,
    @SerializedName("Lat")
    var lat: String,
    @SerializedName("Lng")
    var lng: String,
    @SerializedName("MerchantCode")
    var merchantCode: String,
    @SerializedName("MerchantID")
    var merchantID: String,
    @SerializedName("MerchantLogo")
    var merchantLogo: String,
    @SerializedName("MerchantOpeningHours")
    var merchantOpeningHours: String,
    @SerializedName("MerchantTypeID")
    var merchantTypeID: String,
    @SerializedName("ParticipationLevel")
    var participationLevel: String,
    @SerializedName("PhoneNo")
    var phoneNo: String,
    @SerializedName("PromotionalImage")
    var promotionalImage: String,
    @SerializedName("RefundDays")
    var refundDays: String,
    @SerializedName("RefundPolicy")
    var refundPolicy: String,
    @SerializedName("StoreName")
    var storeName: String,
    @SerializedName("SubCategoryID")
    var subCategoryID: String,
    @SerializedName("SubCategoryName")
    var subCategoryName: String,
    @SerializedName("TowerNumber")
    var towerNumber: String,
    @SerializedName("UnitNumber")
    var unitNumber: String,
    @SerializedName("Website")
    var website: String,
    @SerializedName("Zone")
    var zone: String
)