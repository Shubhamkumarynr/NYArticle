package com.ny.nyarticle.ui.mainScreen.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ny.nyarticle.R
import com.ny.nyarticle.model.DataModel
import com.ny.nyarticle.ui.mainScreen.adapter.ListAdapter
import com.ny.nyarticle.ui.mainScreen.viewmodel.MainViewModel
import com.ny.nyarticle.utils.ConnectionLiveData
import com.ny.nyarticle.utils.DialogAlert
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Response.error

class MainActivity : AppCompatActivity() {
    private var data: ArrayList<DataModel.Result?> = ArrayList()
    lateinit var adapter: ListAdapter
    private lateinit var networkInfo: ConnectionLiveData
    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.colorPrimaryDark)
        }
        setContentView(R.layout.activity_main)

        initToolbar()
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        networkInfo = ConnectionLiveData(this)

        registerNetworkListner()

        getNetworkData()
    }

    private fun getNetworkData() {
        if (networkInfo.isConnected(this)) {/*if network is available, else display error dialog*/
            setRecyclerview()
            getRowData()
        } else showNetworkError()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    /*Set Recycler view properties and Display*/
    private fun setRecyclerview() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(
            this@MainActivity,
            data,
            model
        )

        recyclerView.adapter = adapter
    }


    /**getRowData - get data from view model (Server)*/
    private fun getRowData() {
        model.fetchData.observe(this, Observer<ArrayList<DataModel.Result>> { rowList ->
            if (rowList != null) {
                data.clear()
                data.addAll(rowList)
                adapter.notifyDataSetChanged()

            } else{
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }

        })
    }

    /*Display Network Error*/
    private fun showNetworkError() {
        val dialogAlert = DialogAlert()
        dialogAlert.showErrorDialog(
            this,
            resources!!.getString(R.string.error_title),
            resources!!.getString(R.string.networkerror_meesgae),
            resources!!.getString(R.string.btn_ok)
        )
    }

    /*Network connection Listener */
    private fun registerNetworkListner() {
        networkInfo.observe(this, Observer {
            if (it!!) {
                showSnackbar(R.string.internet_available)
            } else showSnackbar(R.string.internet_not_available)
        })
    }

    /*Display snackbar when network connectivity change*/
    private fun showSnackbar(msg: Int) {
        val snackbar = Snackbar
            .make(constraint_layout, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers()
            return
        }
        super.onBackPressed()
    }


}
