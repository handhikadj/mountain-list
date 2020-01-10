package com.example.dika.submissiondicoding.views


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dika.submissiondicoding.R
import com.example.dika.submissiondicoding.databinding.FragmentDetailBinding
import com.example.dika.submissiondicoding.viewmodelfactories.DetailFragmentViewModelFactory
import com.example.dika.submissiondicoding.viewmodels.DetailFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        val receivingArgs = DetailFragmentArgs.fromBundle(arguments!!)
        val vmFactory = DetailFragmentViewModelFactory(receivingArgs.id)
        val detailFragmentViewModel =
            ViewModelProviders.of(this, vmFactory).get(DetailFragmentViewModel::class.java)

        binding.detailViewModel = detailFragmentViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}
