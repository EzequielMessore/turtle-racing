package br.com.messore.tech.turtleracing.data.remote.model

import com.google.gson.annotations.SerializedName

data class Run(
    @SerializedName("p")
    val position: Int?,
    @SerializedName("t")
    val profit: String?,
    val error: String?
)
