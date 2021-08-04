package com.ny.nyarticle.ui.mainScreen.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ny.nyarticle.R
import com.ny.nyarticle.model.DataModel
import com.ny.nyarticle.ui.detailScreen.DetailActivity
import com.ny.nyarticle.ui.mainScreen.viewmodel.MainViewModel

class ListAdapter(
    private var mCtx: Context,
    private var listData: List<DataModel.Result?>,
    private var model: MainViewModel
) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.listview_layout, parent, false)
        return ListViewHolder(view)

    }

    /*Set data in view*/
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        val data2 = listData[position]

        onbind(holder, data) //set data in list row

    }

    private fun onbind(
        holder: ListViewHolder,
        data: DataModel.Result?

    ) {
        holder.tvTitle.text = data?.title
        holder.tvDescription.text = data?.source
        holder.tvDate.text = data?.published_Date
//        https:\/\/static01.nyt.com\/images\/2019\/12\/16\/opinion\/16webster\/merlin_163910355_fa50244e-1882-4add-bad1-ea314264bba0-thumbStandard.jpg
        /*Images url not working in json data, So displaying placeholder*/
//        val url = data?.url
        Glide.with(mCtx)
            .load("")
            .placeholder(R.drawable.ic_nytimes_icon)
            .apply(RequestOptions.circleCropTransform())//load image in circular shape
            .into(holder.ivAvatar)
        holder.itemView.setOnClickListener {
            onDetailScreen(data?.url, holder.tvTitle)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.title)
        var tvDescription: TextView = itemView.findViewById(R.id.description)
        var tvDate: TextView = itemView.findViewById(R.id.date)
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)

    }


    private fun onDetailScreen(
        url: String?,
        tv_title: TextView

    ) {
        val intent = Intent(mCtx, DetailActivity::class.java)
        intent.putExtra("url", url)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val v2 = androidx.core.util.Pair(
                tv_title as View,
                mCtx.resources.getString(R.string.transition_string)
            )
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(mCtx as Activity, v2)
            mCtx.startActivity(intent, options.toBundle())
        } else {
            mCtx.startActivity(intent)
        }

    }


}

