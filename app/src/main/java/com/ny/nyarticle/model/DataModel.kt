package com.ny.nyarticle.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    inner class Result {
        @SerializedName("uri")
        @Expose
        var uri: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("adx_keywords")
        @Expose
        var adxKeywords: String? = null
        @SerializedName("column")
        @Expose
        var column: String? = null
        @SerializedName("section")
        @Expose
        var section: String? = null
        @SerializedName("byline")
        @Expose
        var byline: String? = null
        @SerializedName("type")
        @Expose
        var type: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("abstract")
        @Expose
        var abstract: String? = null
        @SerializedName("published_date")
        @Expose
        var published_Date: String? = null
        @SerializedName("source")
        @Expose
        var source: String? = null
        @SerializedName("id")
        @Expose
        var id: Long? = null
        @SerializedName("asset_id")
        @Expose
        var assetId: Long? = null
        @SerializedName("views")
        @Expose
        var views: Int? = null
        @SerializedName("media")
        @Expose
        var media: List<Result.Media>? = null

        inner class Media{
            @SerializedName("type")
            @Expose
            var type: String? = null
            @SerializedName("subtype")
            @Expose
            var subtype: String? = null
            inner class media_metadata{
                @SerializedName("url")
                @Expose
                var url: String? = null
            }
        }

    }
}