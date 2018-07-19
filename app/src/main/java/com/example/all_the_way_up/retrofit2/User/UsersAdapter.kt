package com.example.all_the_way_up.retrofit2.User

import android.content.Context
import android.content.Intent
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.Repos.ReposActivity
import com.example.all_the_way_up.retrofit2.data.User
import kotlinx.android.synthetic.main.user_list_item.view.*


class UsersAdapter(val context: Context): RecyclerView.Adapter<usersViewHolder>() {

    var userList : ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usersViewHolder {
        return usersViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.user_list_item,
                        parent,
                        false
                ),
                context
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: usersViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addData(data: ArrayList<User>) {
        userList.addAll(data)
        notifyDataSetChanged()
    }

}

class usersViewHolder (private val mBinder: ViewDataBinding,val context: Context) : RecyclerView.ViewHolder(mBinder.root) {

    fun bind(user : User){
        mBinder.setVariable(BR.userVar,user)

        mBinder.setVariable(BR.handlers,Handlers(context))

        mBinder.executePendingBindings()
    }

}

class Handlers (val context: Context){
    fun onClick(view : View){
        context.startActivity(Intent(context,ReposActivity::class.java)
                .putExtra("name",view.login.text.toString())
                .putExtra("imageUrl",view.hidenUrl.text.toString()))
    }
}

@BindingAdapter("android:src")
fun ImageView.setImageUrl( url: String){
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("dataUser")
fun RecyclerView.bindItem(data: ArrayList<User>){
    (adapter as UsersAdapter).addData(data)
}


