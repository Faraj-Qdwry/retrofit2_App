package com.example.all_the_way_up.retrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.all_the_way_up.retrofit2.Retrofit.callingApiInterface
import com.example.all_the_way_up.retrofit2.Retrofit.retrofiClinte
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*

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

        repossRecyclerView.setHasFixedSize(true)
        repossRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchData(username)


    }

    private fun fetchData(name : String) {

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
        )
    }
}
