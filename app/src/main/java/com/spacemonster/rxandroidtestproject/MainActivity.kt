package com.spacemonster.rxandroidtestproject

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val listAdapter = SimpleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list.adapter = listAdapter
        rv_list.layoutManager = LinearLayoutManager(baseContext)

//        Observable.interval(500,TimeUnit.MILLISECONDS)
//                .map { SimpleData(resources.getDrawable(R.drawable.ic_launcher_foreground),"sample$it") }
//                .take(10)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe (listAdapter::addData)

        getItemObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ( listAdapter::addData )
    }

    private fun getItemObservable(): Observable<SimpleData>{
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        return Observable.fromIterable(packageManager.queryIntentActivities(intent, 0))
                .sorted(ResolveInfo.DisplayNameComparator(packageManager))
                .subscribeOn(Schedulers.io())
                .map {
                    val icon = it.activityInfo.loadIcon(packageManager)
                    val name = it.activityInfo.loadLabel(packageManager).toString()
                    SimpleData(icon , name)
                }
    }

}
