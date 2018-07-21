package com.example.all_the_way_up.retrofit2.Repos

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR.repoName
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.User.UsersAdapter
import com.example.all_the_way_up.retrofit2.data.Repos
import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.databinding.RepoListItemBinding
import kotlinx.android.synthetic.main.repo_list_item.view.*

class reposAdapter() : RecyclerView.Adapter<ViewHolder>() {
    val items = ArrayList<Repos>()

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.repo_list_item,
                        parent,
                        false)
        )
    }

    fun addData(arrayList: ArrayList<Repos>){
        this.items.addAll(arrayList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ViewHolder (private val mBinder: ViewDataBinding) : RecyclerView.ViewHolder(mBinder.root) {
    fun bind(repos: Repos){
        mBinder.setVariable(repoName,repos)
        mBinder.executePendingBindings()
    }
}

@BindingAdapter("dataRepo")
fun RecyclerView.bindItem(data: ArrayList<Repos>){
    (adapter as reposAdapter).addData(data)
}