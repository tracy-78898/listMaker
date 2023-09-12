package com.raywenderlich.listmaker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.listmaker.databinding.MainFragmentBinding


class MainFragment(
    var clickListener:
                   MainFragmentInteractionListener) : Fragment(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }

    override fun listItemClicked(list: TaskList) {
        TODO("Not yet implemented")


    }

    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener) =
            MainFragment(clickListener)

    }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = MainFragmentBinding.inflate(inflater, container, false)
            binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            return binding.root
        }


        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(
                requireActivity(),
                MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
            ).get(MainViewModel::class.java)

            val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
            binding.listsRecyclerview.adapter = recyclerViewAdapter


            viewModel.onListAdded = {
                recyclerViewAdapter.listsUpdated()
            }
        }


    }
}