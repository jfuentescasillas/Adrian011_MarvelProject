package com.example.adrian011_marvelproject.presentation.fragments.characterlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.adrian011_marvelproject.databinding.FragmentCharacterListBinding


class CharacterListFragment : Fragment() {
    lateinit var binding: FragmentCharacterListBinding
    val viewModel: CharacterListViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)

        viewModel.requestInformation()

        // Inflate the layout for this fragment
        return binding.root
    }

}