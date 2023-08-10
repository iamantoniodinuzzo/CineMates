package com.indisparte.media_discover


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.indisparte.media_discover.databinding.FragmentDiscoverBinding
import com.indisparte.ui.fragment.BaseFragment

/**
 *@author Antonio Di Nuzzo
 */
class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverBinding
        get() = FragmentDiscoverBinding::inflate

    override fun initializeViews() {
        //todo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //todo
    }

}