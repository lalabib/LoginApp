package com.lalabib.loginapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.loginapp.R
import com.lalabib.loginapp.adapter.ListUserAdapter
import com.lalabib.loginapp.adapter.LoadingStateAdapter
import com.lalabib.loginapp.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userAdapter: ListUserAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupData() {
        userAdapter = ListUserAdapter()

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = userAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    userAdapter.retry()
                }
            )
        }

        homeViewModel.getAllUser().observe(this@HomeActivity) {
            userAdapter.submitData(lifecycle, it)
        }
    }
}