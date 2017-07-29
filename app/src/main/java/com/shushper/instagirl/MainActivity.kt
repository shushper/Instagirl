package com.shushper.instagirl

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    private val recycler: RecyclerView by bind(R.id.girls_recycler)
    private val progress: ViewGroup by bind(R.id.progress)

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
        showLoad()

        val api = (application as App).api

        disposable = api.girls()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onGirlsLoaded(it) },
                        { onError(it) }
                )
    }

    private fun onError(it: Throwable?) {
        hideLoad()
        Toast.makeText(this, it?.message ?: "unknown error", Toast.LENGTH_SHORT).show()
    }

    private fun onGirlsLoaded(it: List<Girl>) {
        hideLoad()
        adapter.setGirls(it)
    }

    private fun showLoad() {
        progress.visibility = View.VISIBLE
    }

    private fun hideLoad() {
        progress.visibility = View.GONE
    }

}
