package com.example.dika.mountainlist.views

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dika.mountainlist.R
import com.example.dika.mountainlist.adapter.TodoListAdapter
import com.example.dika.mountainlist.databinding.FragmentHomeBinding
import com.example.dika.mountainlist.viewmodels.HomeFragmentViewModel

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private val homeFragmentViewModel by lazy {
        ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
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

        val todoListAdapter = TodoListAdapter(homeFragmentViewModel)

        homeFragmentViewModel.todoList.observe(viewLifecycleOwner, Observer {
            it?.let {
                todoListAdapter.submitList(it)
            }
        })

        homeFragmentViewModel.showNotif.observe(viewLifecycleOwner, Observer {
            it?.let{
                Toast.makeText(context, "apabae", Toast.LENGTH_LONG).show()
            }
        })

        binding.mountainRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = todoListAdapter
        }

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
