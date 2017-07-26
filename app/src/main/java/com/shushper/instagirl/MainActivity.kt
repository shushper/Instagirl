package com.shushper.instagirl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    private val recycler: RecyclerView by bind(R.id.girls_recycler)
    private val adapter: GirlsAdapter = GirlsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

        loadGirls()
    }

    private fun loadGirls() {
        val api = (application as App).api

        disposable = api.girls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onGirlsLoaded(it) }
                )
    }

    private fun onGirlsLoaded(it: List<Girl>) {
        adapter.setGirls(it)
    }

}
