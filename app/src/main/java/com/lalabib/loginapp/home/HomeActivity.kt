package com.lalabib.loginapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.lalabib.loginapp.R
import com.lalabib.loginapp.adapter.ListUserAdapter
import com.lalabib.loginapp.adapter.LoadingStateAdapter
import com.lalabib.loginapp.databinding.ActivityHomeBinding
import com.lalabib.loginapp.login.LoginActivity
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
        setData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupData() {
        homeViewModel.getUser().observe(this@HomeActivity) {
            if (it.token == "") {
                moveToLogin()
            } else {
                getAllUser()
            }
        }
    }

    private fun setData() {
        userAdapter = ListUserAdapter()

        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = userAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    userAdapter.retry()
                }
            )
        }
    }

    private fun getAllUser() {
        homeViewModel.getAllUser().observe(this@HomeActivity) {
            userAdapter.submitData(lifecycle, it)
        }
    }

    private fun moveToLogin() {
        startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val builder = AlertDialog.Builder(this)
        val dialog = builder.setTitle(getString(R.string.confirm_logout))
            .setMessage(getString(R.string.logout_message))
            .setPositiveButton(getString(R.string.yes)) { dialoag, _ ->
                homeViewModel.logout()
                finish()
                dialoag.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) {dialog, _ ->
                dialog.dismiss()
            }
        dialog.show()
    }
}