package com.example.champstats

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.SearchView
import com.example.champstats.databinding.FragmentChampsFilterBinding

class ChampsFilter : Fragment(), SearchView.OnQueryTextListener, OnItemSelectedListener  {
    private lateinit var activityCallback: FilterListener
    private lateinit var binding: FragmentChampsFilterBinding

    interface FilterListener {
        fun setQuery(query: String)
        fun setSort(sort: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChampsFilterBinding.inflate(inflater, container, false)

        binding.searchView.setOnQueryTextListener(this)
        binding.sortSpinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) activityCallback.setQuery(query)
        else activityCallback.setQuery("")
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) activityCallback.setQuery(query)
        else activityCallback.setQuery("")
        return true
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        activityCallback.setSort(binding.sortSpinner.getItemAtPosition(pos) as String)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        activityCallback.setSort("")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as FilterListener
        }
        catch(e : ClassCastException) {
            throw ClassCastException(context.toString() +
                    " must implement FilterListener")
        }
    }
}