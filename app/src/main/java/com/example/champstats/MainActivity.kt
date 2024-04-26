package com.example.champstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity(), ChampsFilter.FilterListener {
    private val viewModel: ChampsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayFragment = supportFragmentManager.findFragmentById(R.id.displayFragmentView) as ChampsDisplay

        viewModel.champions.observe(this) {champList ->
            if(champList.isNotEmpty()) {
                displayFragment.setChampions(champList)
            }
        }
    }

    override fun setQuery(query: String) {
        viewModel.setQuery(query)
    }

    override fun setSort(sort: String) {
        viewModel.setSort(sort)
    }
}