package com.example.champstats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

const val ChampionsURL = "https://ddragon.leagueoflegends.com/cdn/14.8.1/data/en_US/champion.json"

class ChampsViewModel(application: Application) : AndroidViewModel(application) {
    private var query = ""
    private var sort = "Name: A-Z"
    private var allChampsList: List<Champion> = listOf()

    private val _champions = MutableLiveData<List<Champion>>(mutableListOf())
    val champions: LiveData<List<Champion>> = _champions

    init {
        fetchChampions()
    }

    fun setQuery(query: String) {
        this.query = query
        filterChampions()
    }

    fun setSort(sort: String) {
        this.sort = if(sort != "") sort else "Name: A-Z"
        sortChampions()
    }

    private fun sortChampions() {
        val champList = _champions.value
        if(champList.isNullOrEmpty()) return

        when(sort) {
            "Name: A-Z" -> _champions.value = champList.sortedBy { it.name[0] }
            "Name: Z-A" -> _champions.value = champList.sortedByDescending { it.name[0] }
            else -> println("No case for sort $sort")
        }
    }

    private fun filterChampions() {
        val champList = _champions.value
        if(champList.isNullOrEmpty()) return

        if(query == "") {
            _champions.value = allChampsList.toList()
        } else {
            _champions.postValue(allChampsList.filter { champion: Champion -> champion.name.contains(query, true) })
        }

        sortChampions()
    }

    private fun fetchChampions() {
        val champList: MutableList<Champion> = mutableListOf()

        val request = JsonObjectRequest(Method.GET, ChampionsURL, null,
            { response ->
                val champData = response.getJSONObject("data")
                val gson = Gson()

                for (key in champData.keys()) {
                    val champ = gson.fromJson(champData.getString(key), Champion::class.java)
                    champList.add(champ)
                }

                allChampsList = champList
                _champions.value = champList.toList()
            },
            {error ->
                println(error)
            })

        val queue = Volley.newRequestQueue(getApplication<Application>().applicationContext)
        queue.add(request)
    }
}