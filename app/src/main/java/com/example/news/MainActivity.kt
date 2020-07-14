package com.example.news

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var versionVM: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setTitle("Top News Headlines")
        if (Preferece.isNetworkAvailable(this)) {
            versionVM = ViewModelProvider(this).get(NewsViewModel::class.java)
            versionVM.getLiveVersionDetails().observe(this, obsname)
            versionVM.getApiErrorAlert().observe(this, apiAlertObserver)

        } else {
            Toast.makeText(applicationContext, "No Internet connection", Toast.LENGTH_SHORT)
                .show()
            avi.hide()
        }
    }


    var apiAlertObserver: Observer<String> = object : Observer<String> {
        override fun onChanged(t: String?) {
            avi.hide()
            if (!t.equals("")) {
                Toast.makeText(applicationContext, "Please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    var obsname: Observer<NewsResponse> = object : Observer<NewsResponse> {
        override fun onChanged(resp: NewsResponse?) {
            try {
                avi.hide()
                if (resp!!.status.equals("ok")) {
                    rclview!!.setHasFixedSize(true)
                    rclview!!.layoutManager =
                        LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
                    var productAdapter = NewsAdapter(resp.articles, this@MainActivity)
                    rclview.adapter = productAdapter
                } else {
                    Toast.makeText(applicationContext, "Please try again", Toast.LENGTH_SHORT)
                        .show()
                    avi.hide()
                }

            } catch (e: Exception) {
                e.printStackTrace()
                avi.hide()
            }

        }
    }


}
