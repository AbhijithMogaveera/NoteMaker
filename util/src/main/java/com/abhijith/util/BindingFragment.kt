package com.abhijith.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<T:ViewBinding>: Fragment(){

    private var _binding: T? = null
    val binding get() =  _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return onCreateBinding(inflater, container, savedInstanceState).apply {
            _binding = this
        }.root
    }

    abstract fun onCreateBinding(inflater: LayoutInflater,
                                 container: ViewGroup?,
                                 savedInstanceState: Bundle?):T

    @CallSuper
    open fun onDestroyBinding(binding:T){
        _binding = null
    }

    override fun onDestroyView() {
        onDestroyBinding(binding)
        super.onDestroyView()
    }
}