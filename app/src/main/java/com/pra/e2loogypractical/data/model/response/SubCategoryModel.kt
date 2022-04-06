package com.pra.e2loogypractical.data.model.response

import com.google.gson.annotations.SerializedName

data class SubCategoryModel(
    @SerializedName("SubCategoryID")
    var subCategoryID: String,
    @SerializedName("SubCategoryName")
    var subCategoryName: String,
)