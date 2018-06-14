package com.example.all_the_way_up.retrofit2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


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

    private fun displayData(users: List<dataclass>?) {
        Log.d("Main Activity","received data  !!!!!!!!!!!!!!!!!!!!!!!!!!!")
        val adapter = userAdapter(this,users!!)
        usersRecyclerView.adapter = adapter
    }
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