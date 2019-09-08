package com.yogi.credit.data.model

import com.google.gson.annotations.SerializedName

data class BaseMdl<T>(
    @SerializedName("data")
 val data: T
)
