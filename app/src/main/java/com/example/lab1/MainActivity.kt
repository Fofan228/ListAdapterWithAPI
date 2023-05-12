package com.example.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.adapter.MyCardAdapter
import com.example.lab1.common.Common
import com.example.lab1.databinding.ActivityMainBinding
import com.example.lab1.request.CardRequest
import com.example.lab1.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var myService: RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyCardAdapter
//    lateinit var rvCards: RecyclerView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        rvCards = findViewById<View>(R.id.recyclerCardList) as RecyclerView
        myService = Common.retrofitService
//        rvCards.setHasFixedSize(true)
        binding.recyclerCardList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
//        rvCards.layoutManager = layoutManager
        binding.recyclerCardList.layoutManager = layoutManager

        getCardList()
    }

    private fun getCardList() {
        myService.getCardList().enqueue(object : Callback<MutableList<CardRequest>> {
            override fun onFailure(call: Call<MutableList<CardRequest>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<CardRequest>>, response: Response<MutableList<CardRequest>>) {
                adapter = MyCardAdapter(response.body() as MutableList<CardRequest>)
                adapter.notifyDataSetChanged()
                binding.recyclerCardList.adapter = adapter
//                rvCards.adapter = adapter
            }
        })
    }
}