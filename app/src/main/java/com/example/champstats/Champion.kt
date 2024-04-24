package com.example.champstats

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class ChampImage(
    val full: String,
    val sprite: String,
    val group: String,
)

data class ChampInfo(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int,
)

data class Champion(
    val version: String,
    val id: String,
    val key: String,
    val name: String,
    val title: String,
    val blurb: String,

    val info: ChampInfo,
    val image: ChampImage,

    val tags: List<String>,

    @SerializedName("partype")
    val barType: String,
)