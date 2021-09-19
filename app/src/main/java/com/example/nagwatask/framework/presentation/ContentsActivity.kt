package com.example.nagwatask.framework.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.nagwatask.R
import com.example.nagwatask.business.entities.DataState
import com.example.nagwatask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ContentsActivity : AppCompatActivity() {

    private val viewModel: ContentsViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: ContentAdapter

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()

        viewModel.contentsDataState.observe(this, {
            when (it) {
                is DataState.Success -> {
                    adapter.submitList(it.data)
                    Log.d("SUCCESS", "${it.data}")
                }
                is DataState.Loading -> {
                    Log.d("Loading", "${it.isLoading}")
                }
                is DataState.Failure -> {
                    Log.d("Failure", "${it.throwable.message}")
                }
            }
        })
    }

    private fun initUI() {
        binding.contentRV.adapter = adapter
    }
}