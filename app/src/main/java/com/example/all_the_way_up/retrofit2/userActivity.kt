package com.example.all_the_way_up.retrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Adapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.example.all_the_way_up.retrofit2.Retrofit.callingApiInterface
import com.example.all_the_way_up.retrofit2.Retrofit.retrofiClinte
import com.example.all_the_way_up.retrofit2.data.repos
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class userActivity : AppCompatActivity() {

    internal lateinit var jsonApi: callingApiInterface
    //internal lateinit var compositeDisposable: CompositeDisposable
    private var compositeDisposable = AndroidDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val username = intent.extras.getString("name")
        val imageUrl = intent.extras.getString("imageUrl")
        // Image and name
        Glide.with(this).load(imageUrl).into(imageView)
        nameView.text = username


        // Initilazing the API
        val retrofit = retrofiClinte.instances
        jsonApi = retrofit.create(callingApiInterface::class.java)

        //recyclerView
        repossRecyclerView.setHasFixedSize(true)
        repossRecyclerView.layoutManager = LinearLayoutManager(this)

        jsonApi.listRepos(username).enqueue(object : Callback<List<repos>> {
            override fun onResponse(call: Call<List<repos>>?, response: Response<List<repos>>?) {
                var listofrepos = response?.body()!!
                repossRecyclerView.adapter = reposAdapter(response?.body()!!, this@userActivity)

            }

            override fun onFailure(call: Call<List<repos>>?, t: Throwable?) {
            }
        })

        fetchData(username)


    }

    private fun fetchData(name: String) {

//        compositeDisposable.add(jsonApi.listRepos(name)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe ({ result ->
//                    Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
//                }, {
//                    error -> error.printStackTrace()
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({users->displayData(users)})
//        )
    }
}
