package com.example.dika.submissiondicoding.views

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dika.submissiondicoding.R
import com.example.dika.submissiondicoding.adapter.TodoListAdapter
import com.example.dika.submissiondicoding.adapter.TodoListAdapterOnClickListener
import com.example.dika.submissiondicoding.databinding.FragmentHomeBinding
import com.example.dika.submissiondicoding.viewmodels.HomeFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val homeFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        setHasOptionsMenu(true)

        binding.homeViewModel = homeFragmentViewModel

        binding.lifecycleOwner = this

        // val onClickAdapter = MountainListAdapter(MountainListAdapterOnClickListener {
        //     homeFragmentViewModel.navigateToDetail(it)
        // })

        val onClickAdapter = TodoListAdapter(TodoListAdapterOnClickListener {
            homeFragmentViewModel.navigateToDetail(it)
        })

        homeFragmentViewModel.navigateToDetail.observe(this, Observer {
            it?.let {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                action.id = it
                if (this.findNavController().currentDestination?.id === R.id.homeFragment) {
                    this.findNavController().navigate(action)
                    homeFragmentViewModel.navigatedToDetail()
                }
            }
        })

        homeFragmentViewModel.getTodo()

        binding.mountainRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = onClickAdapter
        }

        // homeFragmentViewModel.mountainListData.observe(this, Observer {
        //     it.apply {
        //         onClickAdapter.submitList(it.dataList)
        //     }
        // })

        homeFragmentViewModel.todoListData.observe(viewLifecycleOwner, Observer {
            it?.apply {
                onClickAdapter.submitList(it)
            }
        })

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_right_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
