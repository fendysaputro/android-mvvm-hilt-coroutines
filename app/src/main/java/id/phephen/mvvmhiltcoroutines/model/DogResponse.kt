package id.phephen.mvvmhiltcoroutines.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Phephen on 30/05/2022.
 */
data class DogResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)