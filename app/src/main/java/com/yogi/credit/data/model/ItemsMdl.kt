package com.yogi.credit.data.model

import com.google.gson.annotations.SerializedName

data class ItemsMdl(
    @SerializedName("article_image")
    val articleImage: String = "",
    @SerializedName("article_title")
    val articleTitle: String = "",
    @SerializedName("product_image")
    val productImage: String = "",
    @SerializedName("product_name")
    val productTitle: String = "",
    @SerializedName("link")
    val link: String = ""
)