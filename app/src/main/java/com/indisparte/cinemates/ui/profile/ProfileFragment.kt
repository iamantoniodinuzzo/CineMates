package com.indisparte.cinemates.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indisparte.cinemates.databinding.FragmentProfileBinding
import com.indisparte.ui.fragment.BaseFragment

/**
 * @author Antonio Di Nuzzo
 * Created 28/08/2022 at 10:13
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate

    override fun initializeViews() {
        TODO("Not yet implemented")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }

}