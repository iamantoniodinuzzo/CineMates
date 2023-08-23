package com.indisparte.person_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indisparte.person_details.databinding.FragmentPersonDetailsContainerBinding
import com.indisparte.ui.fragment.BaseFragment
import timber.log.Timber

class PersonDetailsFragment : BaseFragment<FragmentPersonDetailsContainerBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonDetailsContainerBinding
        get() = FragmentPersonDetailsContainerBinding::inflate
    private val LOG = Timber.tag(PersonDetailsFragment::class.java.simpleName)

    override fun initializeViews() {
        //init views here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here
    }

}