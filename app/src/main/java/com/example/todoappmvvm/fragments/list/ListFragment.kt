package com.example.todoappmvvm.fragments.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.viewmodel.ToDoViewModel
import com.example.todoappmvvm.databinding.FragmentListBinding

class ListFragment : Fragment() {
    //Binding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    //Other
    private val adapter: ListAdapter by lazy { ListAdapter() }
    private val mTodoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set menu
        setHasOptionsMenu(true)
        binding.apply {
            floatingActionButton.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_addFragment)
            }
            listLayout.setOnClickListener {
                findNavController().navigate(R.id.action_listFragment_to_updateFragment)
            }
            //Setup recycler
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            //Observe
            mTodoViewModel.getAllData.observe(viewLifecycleOwner) { data ->
                adapter.setData(data)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    fun onsDestroyView() {
        _binding = null
    }
}