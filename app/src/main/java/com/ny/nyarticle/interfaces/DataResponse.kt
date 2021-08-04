package com.ny.nyarticle.interfaces

import com.ny.nyarticle.model.DataModel

interface DataResponse {
    fun onResponse(body: DataModel?)
    fun onError()
}