package com.example.champstats

import com.google.gson.annotations.SerializedName

data class ChampInfo(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int,
)

data class ChampImage(
    val full: String,
    val sprite: String,
    val group: String,
)

data class ChampStats(
    val hp: Double,
    val hpperlevel: Double,
    val mp: Double,
    val mpperlevel: Double,
    val movespeed: Int,
    val armor: Double,
    val armorperlevel: Double,
    val spellblock: Double,
    val spellblockperlevel: Double,
    val attackrange: Int,
    val hpregen: Double,
    val hpregenperlevel: Double,
    val mpregen: Double,
    val mpregenperlevel: Double,
    val crit: Double,
    val critperlevel: Double,
    val attackdamage: Double,
    val attackdamageperlevel: Double,
    val attackspeedperlevel: Double,
    val attackspeed: Double,
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
    val stats: ChampStats,
)