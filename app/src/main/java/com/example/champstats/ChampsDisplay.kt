package com.example.champstats

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.champstats.databinding.FragmentChampsDisplayBinding

class ChampsDisplay : Fragment() {
    private lateinit var binding: FragmentChampsDisplayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChampsDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setChampions(champList: List<Champion>) {
        val champAdapter = context?.let { ChampAdapter(it,champList) }
        binding.recyclerView.adapter = champAdapter

        val layoutManager: LayoutManager = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        else GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

        binding.recyclerView.layoutManager = layoutManager
    }
}