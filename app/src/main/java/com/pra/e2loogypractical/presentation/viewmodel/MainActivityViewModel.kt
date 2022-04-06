package com.pra.e2loogypractical.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pra.e2loogypractical.data.model.request.MerchantRequest
import com.pra.e2loogypractical.data.model.response.MerchantApiResponse
import com.pra.e2loogypractical.data.model.response.Result
import com.pra.e2loogypractical.data.model.response.SubCategoryModel
import com.pra.e2loogypractical.domain.APPRepository
import com.pra.interview.data.api.WebApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(private val app: Context) : ViewModel() {

    private val _countryLoadError = MutableLiveData<Boolean>()
    private val _loading = MutableLiveData<Boolean>()
    private val _subCategoryListUpdate = MutableLiveData<List<SubCategoryModel>>()
    private val _resultListUpdate = MutableLiveData<List<Result>>()

    val countryLoadError: LiveData<Boolean> = _countryLoadError
    val loading: LiveData<Boolean> = _loading
    val subCategoryListUpdate: LiveData<List<SubCategoryModel>> = _subCategoryListUpdate
    val resultUpdate: LiveData<List<Result>> = _resultListUpdate

    private lateinit var modelResponse: MerchantApiResponse

    private var _respository: APPRepository = APPRepository(app)
    private lateinit var _responseCall: Call<MerchantApiResponse>

    init {
    }

    public fun fetchMerchant(boolean: Boolean) {
        if (boolean) {
            _loading.value = true
        }
        _responseCall = _respository.getMerchantList(onPrepareRequestBody())
        _responseCall.enqueue(object : Callback<MerchantApiResponse> {
            override fun onResponse(
                call: Call<MerchantApiResponse>,
                response: Response<MerchantApiResponse>
            ) {
                _loading.value = false
                when {
                    response.isSuccessful -> {
                        modelResponse = response.body()!!
                        generateSubCategoryList(modelResponse)
                    }
                    response.errorBody() != null -> {
                        _countryLoadError.value = true
                    }
                    else -> {
                        _countryLoadError.value = true
                    }
                }
            }

            override fun onFailure(call: Call<MerchantApiResponse>, t: Throwable) {
                _loading.value = false
                _countryLoadError.value = true
            }
        })
    }


    private fun generateSubCategoryList(model: MerchantApiResponse) {
        var resultList = model.result
        var subCategoryList: MutableList<SubCategoryModel> = ArrayList()
        for (i in resultList.indices) {
            var model = SubCategoryModel(resultList[i].subCategoryID, resultList[i].subCategoryName)
            var isAlreadyAvailable = false
            for (j in subCategoryList.indices) {
                if (subCategoryList[j].subCategoryID.equals(resultList[i].subCategoryID)) {
                    isAlreadyAvailable = true
                    break
                }
            }
            if (!isAlreadyAvailable) {
                subCategoryList.add(model)
            }
        }

        subCategoryList.sortWith(Comparator { obj1, obj2 ->
            obj1.subCategoryName.compareTo(obj2.subCategoryName, ignoreCase = true)
        })

        var isAlreadyAvailable = false
        for (j in subCategoryList.indices) {
            if (subCategoryList[j].subCategoryID.equals("-1")) {
                isAlreadyAvailable = true
                break
            }
        }
        if (!isAlreadyAvailable) {
            subCategoryList.add(0, SubCategoryModel("-1", "ALL"))
        }

        _subCategoryListUpdate.value = subCategoryList
        _resultListUpdate.value = resultList
    }


    public fun getFilteredData(subCategoryId: String) {
        if (modelResponse != null) {
            var resultList = modelResponse.result

            var resultFilteredList: MutableList<Result> = ArrayList()
            if (subCategoryId != "-1") {
                for (i in resultList.indices) {
                    if (resultList[i].subCategoryID == subCategoryId) {
                        resultFilteredList.add(resultList[i])
                    }
                }
            } else {
                resultFilteredList = resultList as MutableList<Result>
            }
            _resultListUpdate.value = resultFilteredList
        }
    }


    private fun onPrepareRequestBody(): Map<String, String> {
        val mInfo = MerchantRequest(
            1, "small", 0, "", 0
        )
        val request = Gson().toJson(mInfo)
        val params: MutableMap<String, String> = HashMap()
        params["params"] = request
        return params
    }


    override fun onCleared() {
        _responseCall.cancel()
        super.onCleared()
    }
}