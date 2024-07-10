package com.example.freelancefinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.freelancefinder.databinding.ActivityMainBinding
import com.example.freelancefinder.db.RemoteJobDatabase
import com.example.freelancefinder.repository.RemoteJobRepository
import com.example.freelancefinder.viewmodel.RemoteJobViewModel
import com.example.freelancefinder.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: RemoteJobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setUpViewModel()

    }

    private fun setUpViewModel() {

        val remoteJobRepository = RemoteJobRepository(
            RemoteJobDatabase(this)
        )

        val viewModelProviderFactory =
            RemoteJobViewModelFactory(
                application,
                remoteJobRepository
            )

        viewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(RemoteJobViewModel::class.java)

    }
}