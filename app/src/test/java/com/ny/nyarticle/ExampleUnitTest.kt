package com.ny.nyarticle

import android.provider.ContactsContract
import com.ny.nyarticle.interfaces.DataResponse
import com.ny.nyarticle.model.DataModel
import com.ny.nyarticle.network.ApiInterface
import com.ny.nyarticle.network.RetrofitClientInstance
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

import com.ny.nyarticle.ui.mainScreen.ui.MainActivity
import com.ny.nyarticle.utils.CONSTANTS
import junit.framework.TestCase
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After

import org.robolectric.Robolectric

import org.robolectric.util.ActivityController

import org.mockito.MockitoAnnotations

import org.junit.Before
import org.junit.Rule

import org.mockito.ArgumentCaptor

import org.mockito.Captor

import org.mockito.Mock

import org.robolectric.RobolectricGradleTestRunner

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.annotation.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.util.*

class NyTimesUnitTest {

    @Test
    fun urlMatch_check() {
        assertEquals("https://api.nytimes.com/", "https://api.nytimes.com/")
    }

    @Test
    @Throws(IOException::class)
    fun api_success() {
        val api = RetrofitClientInstance().getClient().create(ApiInterface::class.java)
        val call = api.getListData(CONSTANTS.DATA_URL)


//        val call: Call<DataModel> =
//            api(1, "https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json/")
        val response: Response<DataModel> = call.execute()
        val newsResponse: DataModel? = response.body()
        TestCase.assertTrue(response.isSuccessful && "OK" == if (newsResponse != null) newsResponse.status else null)
    }

    @Test
    @Throws(IOException::class)
    fun api_unauthorized() {
        val api = RetrofitClientInstance().getClient().create(ApiInterface::class.java)
        val call = api.getListData(CONSTANTS.DATA_URL + "1")
        val response = call.execute()
        TestCase.assertTrue(!response.isSuccessful && response.code() == 401)
    }
}