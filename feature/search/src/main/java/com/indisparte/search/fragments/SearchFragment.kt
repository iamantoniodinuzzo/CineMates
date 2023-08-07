package com.indisparte.search.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indisparte.search.databinding.FragmentSearchBindingBinding
import com.indisparte.ui.fragment.BaseFragment

/**
 *@author Antonio Di Nuzzo
 */
class SearchFragment : BaseFragment<FragmentSearchBindingBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBindingBinding
        get() = FragmentSearchBindingBinding::inflate

    override fun initializeViews() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}