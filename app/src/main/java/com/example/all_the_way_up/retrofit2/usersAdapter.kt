package com.example.all_the_way_up.retrofit2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.all_the_way_up.retrofit2.data.User


class userAdapter(internal var context : Context,internal var userList: List<User>)
    : RecyclerView.Adapter<usersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item,parent,false)
        return usersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: usersViewHolder, position: Int) {
        holder.login.text = userList[position].login.toString()
        holder.type.text = userList[position].type.toString()

        val imagurl = userList[position].avatar_url.toString()


        holder.hidentext.text = imagurl

        // glid it here
        Glide.with(context)
                .load(imagurl)
                .into(holder.image)

    }


}