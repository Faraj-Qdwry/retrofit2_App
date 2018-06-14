package com.example.all_the_way_up.retrofit2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.all_the_way_up.retrofit2.Retrofit.callingApiInterface
import com.example.all_the_way_up.retrofit2.Retrofit.retrofiClinte
import com.example.all_the_way_up.retrofit2.data.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_list_item.view.*


class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi: callingApiInterface
    //internal lateinit var compositeDisposable: CompositeDisposable
    private var compositeDisposable = AndroidDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initilazing the API
        val retrofit = retrofiClinte.instances
        jsonApi = retrofit.create(callingApiInterface::class.java)

        // recycler View
        usersRecyclerView.setHasFixedSize(true)
        usersRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchData()

    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.usres
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({users->displayData(users)})
        )
    }

    private fun displayData(users: List<User>?) {
        Log.d("Main Activity","received data  !!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val adapter = userAdapter(this,users!!)
        usersRecyclerView.adapter = adapter
        //putting click listner
        // Usage:
        usersRecyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                val name = view.login.text.toString()
                val imageUrl = view.hidenUrl.text.toString()

                val myActivity = Intent(applicationContext, userActivity::class.java) //name of activity to launch
                myActivity.putExtra("name",name)
                myActivity.putExtra("imageUrl",imageUrl)

                startActivity(myActivity) //to launch another activity
            }
        })
    }
}

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View?) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View?) {
            view?.setOnClickListener({
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            })
        }
    })
}

class AndroidDisposable {
    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }

    fun dispose() {
        compositeDisposable?.dispose()
        compositeDisposable = null
    }
}