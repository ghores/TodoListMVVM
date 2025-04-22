package com.example.todoappmvvm.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoappmvvm.R
import com.example.todoappmvvm.data.models.Priority
import com.example.todoappmvvm.data.models.ToDoData
import com.example.todoappmvvm.data.viewmodel.ToDoViewModel
import com.example.todoappmvvm.databinding.FragmentAddBinding
import com.example.todoappmvvm.fragments.SharedViewModel

class AddFragment : Fragment() {
    //Binding
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    //Other
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set menu
        setHasOptionsMenu(true)
        //InitViews
        binding.apply {
            prioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        binding.apply {
            val mTitle = titleEt.text.toString()
            val mPriority = prioritiesSpinner.selectedItem.toString()
            val mDescription = descriptionEt.text.toString()
            val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
            if (validation) {
                //Insert data to database
                val newData =
                    ToDoData(0, mTitle, mSharedViewModel.parsePriority(mPriority), mDescription)
                mToDoViewModel.insertData(newData)
                Toast.makeText(requireContext(), "Successfully Added!!!", Toast.LENGTH_SHORT).show()
                //Navigate back
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}