package com.gojek_assignment_android.model

import com.google.gson.annotations.SerializedName

class TrendingRepositories_model {
    @SerializedName("author")
    var author: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("avatar")
    var avatar: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("language")
    var language: String? = null
    @SerializedName("languageColor")
    var languageColor: String? = null
    @SerializedName("stars")
    var stars: Int? = null
    @SerializedName("forks")
    var forks: Int? = null
    @SerializedName("currentPeriodStars")
    var currentPeriodStars: Int? = null
    @SerializedName("builtBy")
    var builtBy: List<BuiltBy_Model>? = null

}