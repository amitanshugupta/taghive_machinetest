package com.taghive.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taghive.R
import com.taghive.models.TickersModel
import kotlinx.android.synthetic.main.item_ticker.view.*

class TickersAdapter(
    private var winnerList: ArrayList<TickersModel>,
    private var from: String
) : RecyclerView.Adapter<TickersAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ticker, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_name.text = winnerList[position].baseAsset.uppercase()
        holder.itemView.tv_asset_name.text = " / $from"
        holder.itemView.tv_volume.text = winnerList[position].volume
        holder.itemView.tv_open_price.text = winnerList[position].openPrice
    }

    override fun getItemCount(): Int {
        return winnerList.size
    }
}