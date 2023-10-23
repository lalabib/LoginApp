package com.lalabib.loginapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.lalabib.loginapp.R
import com.lalabib.loginapp.data.remote.network.Result
import com.lalabib.loginapp.databinding.ActivityLoginBinding
import com.lalabib.loginapp.domain.model.User
import com.lalabib.loginapp.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                login(email, password)
            }
        }
    }

    private fun login(email: String, password: String) {
        binding.apply {
            when {
                email.isEmpty() -> {
                    edtEmail.error = getString(R.string.cant_empty)
                }

                password.isEmpty() -> {
                    edtPassword.error = getString(R.string.cant_empty)
                }

                else -> {
                    postLogin(email, password)
                }
            }
        }
    }

    private fun postLogin(email: String, password: String) {
        binding.apply {
            loginViewModel.postLogin(email, password)
                .observe(this@LoginActivity) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    this@LoginActivity,
                                    R.string.login_success,
                                    Toast.LENGTH_SHORT
                                ).show()

                                saveUser(User(email, password, result.data.token))
                                moveToHome()
                            }

                            is Result.Error -> {
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    this@LoginActivity,
                                    R.string.login_failed,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
        }
    }

    private fun saveUser(userResult: User) {
        loginViewModel.saveUser(userResult)
    }

    private fun moveToHome() {
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }
}