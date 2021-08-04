package com.ny.nyarticle.ui.mainScreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ny.nyarticle.interfaces.DataResponse
import com.ny.nyarticle.model.DataModel
import com.ny.nyarticle.network.CommonNetworkRequest

class MainViewModel : ViewModel(), DataResponse {

    private var rowList: MutableLiveData<ArrayList<DataModel.Result>>? = null
    private var comonRequest = CommonNetworkRequest(this)


    /*To get the Row data*/
    val fetchData: MutableLiveData<ArrayList<DataModel.Result>>
        get() {
            if (rowList == null) {
                rowList = MutableLiveData()
                comonRequest.loadNetworkData()  /**loadNetworkData - Using Retrofit to get the JSON data from URL*/

            }
            return rowList as MutableLiveData<ArrayList<DataModel.Result>>
        }

    /*Handling Response*/
    private fun setListData(body: DataModel?) {
        if (body != null) {

            rowList?.value = body.results as ArrayList<DataModel.Result>?
        }
    }

    override fun onResponse(body: DataModel?) {
        setListData(body)
    }

    override fun onError() {
        setListData(null)
    }


}
