package com.example.all_the_way_up.retrofit2.Repos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.data.Repos
import kotlinx.android.synthetic.main.repo_list_item.view.*

class reposAdapter( val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    val items = ArrayList<Repos>()
    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(arrayList: ArrayList<Repos>){
        this.items.addAll(arrayList)
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.rempName?.text = items[position].name.toString()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val rempName = view.repoName
}