package com.e.swaplistproject.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.swaplistproject.R
import com.e.swaplistproject.adapter.AdapterSwappingList
import com.e.swaplistproject.callback.RecycleViewRowMoveCallback
import com.e.swaplistproject.databinding.ActivityMainBinding
import com.e.swaplistproject.model.ModelSwap

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: List<ModelSwap>
    lateinit var adapter: AdapterSwappingList
    private lateinit var manager2: RecyclerView.LayoutManager
    private lateinit var callback: ItemTouchHelper.Callback
    private lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = this
        initView()
    }

    private fun initView() {
        list = ArrayList()
        val value = ModelSwap().apply {
            image = R.drawable.carimage1
        }
        val value2 = ModelSwap().apply {
            image = R.drawable.redcarimage
        }

        for (i in 1..5) {
            (list as ArrayList<ModelSwap>).add(value)
            (list as ArrayList<ModelSwap>).add(value2)
        }
        adapter = AdapterSwappingList(context, list)

        manager2 = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.swapRecycle.layoutManager = manager2

        //for add for adapter to touch on view List
        callback = RecycleViewRowMoveCallback(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.swapRecycle)
        binding.swapRecycle.adapter = adapter
    }
}