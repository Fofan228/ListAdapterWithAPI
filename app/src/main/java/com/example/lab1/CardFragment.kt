package com.example.lab1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.adapter.MyCardAdapter
import com.example.lab1.common.Common
import com.example.lab1.databinding.CardFragmentBinding
import com.example.lab1.request.CardRequest
import com.example.lab1.service.RetrofitService
import com.example.lab1.viewModel.CardVm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardFragment : Fragment() {

    lateinit var binding: CardFragmentBinding
    lateinit var myService: RetrofitService
    lateinit var adapter: MyCardAdapter
    lateinit var cardVm: CardVm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardFragmentBinding.inflate(inflater, container, false)
        getCardList()
        binding.recyclerCardList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        binding.recyclerCardList.adapter = adapter

        return binding.root
    }

    private fun getCardList() {
        adapter = MyCardAdapter()
        myService = Common.retrofitService
        cardVm = ViewModelProvider(this)[CardVm::class.java]

        cardVm.getCardLiveData().observe(viewLifecycleOwner, Observer { cards ->
            adapter.submitList(cards)
        })

        myService.getCardList().enqueue(object : Callback<MutableList<CardRequest>> {
            override fun onFailure(call: Call<MutableList<CardRequest>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<CardRequest>>,
                response: Response<MutableList<CardRequest>>
            ) {
                val cards = response.body()
                if (cards != null) cardVm.setData(cards)
            }
        })
    }
}