package com.example.all_the_way_up.retrofit2

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.user_list_item.view.*

class usersViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val login = itemView.login
    val type = itemView.Type
    val image = itemView.avatarImage
}