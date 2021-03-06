package com.ny.nyarticle.network

import com.ny.nyarticle.interfaces.DataResponse
import com.ny.nyarticle.model.DataModel
import com.ny.nyarticle.utils.CONSTANTS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonNetworkRequest(var dataResponse: DataResponse) {
    /**loadNetworkData - Using Retrofit to get the JSON data from URL*/
     fun loadNetworkData() {
        val api = RetrofitClientInstance().getClient().create(ApiInterface::class.java)
        val call = api.getListData(CONSTANTS.DATA_URL)

        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                dataResponse.onResponse(response.body())
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                dataResponse.onError()
            }
        })
    }
}