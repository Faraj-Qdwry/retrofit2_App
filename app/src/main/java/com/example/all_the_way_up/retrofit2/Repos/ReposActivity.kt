package com.example.all_the_way_up.retrofit2.Repos

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import com.android.databinding.library.baseAdapters.BR.dataRepos
import com.android.databinding.library.baseAdapters.BR.userVar
import com.bumptech.glide.Glide
import com.example.all_the_way_up.retrofit2.DataSource.DataSource
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.DataSource.retrofiClinte
import com.example.all_the_way_up.retrofit2.data.Repos
import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.repos_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposActivity : AppCompatActivity() {

    val reposAdapter by lazy { reposAdapter() }

    private val data: ObservableArrayList<Repos> = ObservableArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.repos_activity)
        var mBinder : ViewDataBinding = DataBindingUtil.setContentView(this,R.layout.repos_activity)

        var user = User()
        with(intent.extras){
            user.login = intent.extras.getString("name")
            user.avatar_url = intent.extras.getString("imageUrl")
        }

        mBinder.setVariable(userVar,user)
        mBinder.setVariable(dataRepos,data)

        // Image and name
        Glide.with(this).load(user.avatar_url).into(imageView)
        nameView.text = user.login


        val retrofit = retrofiClinte.instances

        //recyclerView
        with(repossRecyclerView){
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ReposActivity)
            adapter = reposAdapter
        }

        //Call back

        retrofit.listRepos(user.login).enqueue(object : Callback<ArrayList<Repos>> {
            override fun onResponse(call: Call<ArrayList<Repos>>, response: Response<ArrayList<Repos>>) {
                data.addAll(response.body()!!)
                //reposAdapter.addData(response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<Repos>>, t: Throwable?) {
            }
        })




    }

}
