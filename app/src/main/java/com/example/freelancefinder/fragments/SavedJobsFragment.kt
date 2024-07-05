package com.example.freelancefinder.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freelancefinder.MainActivity
import com.example.freelancefinder.R
import com.example.freelancefinder.databinding.FragmentSavedJobsBinding
import com.example.freelancefinder.models.JobToSave
import com.google.android.material.snackbar.Snackbar


class SavedJobsFragment : Fragment(R.layout.fragment_saved_jobs) {

    private var _binding: FragmentSavedJobsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedJobsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        binding.rvJobsSaved.apply {

            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL) {})

        }

    }

    private fun updateUI(list: List<JobToSave>) {

        if (list.isNotEmpty()) {
            binding.rvJobsSaved.visibility = View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        } else {
            binding.rvJobsSaved.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }

    fun onItemClick(job: JobToSave, view: View, position: Int) {
       deleteJob(job)
    }

    private fun deleteJob(job: JobToSave) {

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Job")
            setMessage("Are you sure you want to permanently delete this job?")
            setPositiveButton("DELETE") { _, _ ->
               Toast.makeText(activity,"Job deleted", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}