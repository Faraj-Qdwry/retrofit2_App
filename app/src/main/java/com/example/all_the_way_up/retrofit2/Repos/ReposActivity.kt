package com.example.all_the_way_up.retrofit2.Repos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.all_the_way_up.retrofit2.DataSource.DataSource
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.DataSource.retrofiClinte
import com.example.all_the_way_up.retrofit2.data.repos
import kotlinx.android.synthetic.main.repos_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReposActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repos_activity)

        val username = intent.extras.getString("name")
        val imageUrl = intent.extras.getString("imageUrl")
        // Image and name
        Glide.with(this).load(imageUrl).into(imageView)
        nameView.text = username


        val retrofit = retrofiClinte.instances

        //recyclerView
        repossRecyclerView.setHasFixedSize(true)
        repossRecyclerView.layoutManager = LinearLayoutManager(this)

        //Call back

        retrofit.listRepos(username).enqueue(object : Callback<ArrayList<repos>> {
            override fun onResponse(call: Call<ArrayList<repos>>?, response: Response<ArrayList<repos>>?) {
                repossRecyclerView.adapter = reposAdapter(response?.body()!!, this@ReposActivity)
            }

            override fun onFailure(call: Call<ArrayList<repos>>?, t: Throwable?) {
            }
        })




    }

}
