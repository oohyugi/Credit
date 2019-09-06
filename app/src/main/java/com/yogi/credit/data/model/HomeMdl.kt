package com.yogi.credit.data.model

import com.google.gson.annotations.SerializedName

data class HomeMdl(@SerializedName("section_title")
                    val sectionTitle: String = "",
                   @SerializedName("section")
                    val section: String = "",
                   @SerializedName("items")
                    val items: List<ItemsMdl>?)