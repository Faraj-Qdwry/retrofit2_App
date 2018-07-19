package com.example.all_the_way_up.retrofit2.User

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.all_the_way_up.retrofit2.BR
import com.example.all_the_way_up.retrofit2.R
import com.example.all_the_way_up.retrofit2.DataSource.AndroidDisposable
import com.example.all_the_way_up.retrofit2.DataSource.DataSource
import com.example.all_the_way_up.retrofit2.DataSource.retrofiClinte
import com.example.all_the_way_up.retrofit2.data.User
import com.example.all_the_way_up.retrofit2.Repos.ReposActivity
import com.example.all_the_way_up.retrofit2.databinding.ActivityMainBinding
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_list_item.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var retrofit: DataSource
    private val compositeDisposable = AndroidDisposable()
    private val data: ObservableArrayList<User> = ObservableArrayList()
    //lateinit var useradapter : userAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mBinder = DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main)

        //useradapter = userAdapter(this@MainActivity)

        mBinder.dataList = data

        retrofit = retrofiClinte.instances

        with(usersRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            //adapter = useradapter
        }

        fetchData()
    }


    private fun fetchData() {
        for (i in 0..2) {
            compositeDisposable.add(
                    retrofit.getUsres()
                            .subscribeOn(Schedulers.io())
                            .subscribe { users -> displayData(users) }
            )
        }
    }

    private fun displayData(users: ArrayList<User>) = data.addAll(users)


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}

