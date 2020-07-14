package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_news_details.*
import java.lang.Exception

class NewsDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        try {
            supportActionBar!!.setTitle("News Detail")
            if (intent != null) {
                if (!TextUtils.isEmpty(intent.getStringExtra("image")))
                    Picasso.get().load(intent.getStringExtra("image")).placeholder(R.drawable.placeholder).into(
                        ivImage
                    )
                tvTitle.setText(intent.getStringExtra("title"))
                tvDesc.setText(intent.getStringExtra("desc"))
                if (!TextUtils.isEmpty(intent.getStringExtra("source")))
                    tvsource.setText(intent.getStringExtra("source"))
                tvDate.text = (intent.getStringExtra("datetime"))
            }
        } catch (e: Exception) {
            Log.e("NewDetail", "" + e.printStackTrace())
        }

    }
}
