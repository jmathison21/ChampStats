package com.example.champstats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

const val ChampSquareURL = "https://ddragon.leagueoflegends.com/cdn/14.8.1/img/champion/%s"

class ChampAdapter(private val context: Context, private val champList: List<Champion>) : RecyclerView.Adapter<ChampAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.champ_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val champ: Champion = champList[position]

        holder.champName.text = champ.name
        holder.champTitle.text = champ.title
        holder.champBlurb.text = champ.blurb

        val infoTemplate = context.resources.getString(R.string.champ_info_template)

        holder.damageText.text = String.format(infoTemplate,champ.info.attack)
        holder.magicText.text = String.format(infoTemplate,champ.info.magic)
        holder.defenseText.text = String.format(infoTemplate,champ.info.defense)
        holder.speedText.text = champ.stats.movespeed.toString()

        //set champion image in one line with Glide
        Glide.with(context)
            .load(String.format(ChampSquareURL, champ.image.full))
            .fitCenter()
            .placeholder(R.drawable.person_placeholder)
            .into(holder.champIcon)
    }

    override fun getItemCount(): Int {
        return champList.size
    }

    class ViewHolder(champView: View) : RecyclerView.ViewHolder(champView) {
        val champName : TextView = champView.findViewById(R.id.champNameView)
        val champTitle : TextView = champView.findViewById(R.id.champTitleView)
        val champBlurb : TextView = champView.findViewById(R.id.champBlurbView)

        val damageText : TextView = champView.findViewById(R.id.infoDamageTextView)
        val magicText : TextView = champView.findViewById(R.id.infoMagicTextView)
        val defenseText : TextView = champView.findViewById(R.id.infoDefenseTextView)
        val speedText : TextView = champView.findViewById(R.id.statSpeedTextView)

        val champIcon : ImageView = champView.findViewById(R.id.champIconView)
    }
}