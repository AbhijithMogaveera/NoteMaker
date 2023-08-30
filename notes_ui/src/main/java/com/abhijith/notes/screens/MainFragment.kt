package com.abhijith.notes.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.abhijith.notes.R
import com.abhijith.notes.databinding.NoteMainFragmentBinding
import com.abhijith.util.BindingFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment: BindingFragment<NoteMainFragmentBinding>() {

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): NoteMainFragmentBinding {
        return NoteMainFragmentBinding.inflate(inflater, container, false)
    }
}