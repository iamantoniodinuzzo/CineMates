package com.indisparte.list_creation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.indisparte.list.MediaList
import com.indisparte.list_creation.databinding.FragmentMediaListCreationBinding
import com.indisparte.network.whenResources
import com.indisparte.ui.fragment.BaseFragment
import com.indisparte.util.Validator
import com.indisparte.util.extension.collectIn
import com.indisparte.util.nameValidationRule
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 *@author Antonio Di Nuzzo
 */
@AndroidEntryPoint
class MediaListCreationFragment : BaseFragment<FragmentMediaListCreationBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMediaListCreationBinding
        get() = FragmentMediaListCreationBinding::inflate
    private val LOG = Timber.tag(MediaListCreationFragment::class.java.simpleName)

    private val viewModel: MediaListCreationViewModel by viewModels()
    private val validator by lazy { Validator(binding) }


    private val textWatcherToEnableSaveButton = object : TextWatcher {
        private var isNameModified = false
        private var isDescriptionModified = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //nothing to do
        }

        override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int,
        ) {
            //Nothing to do
        }

        override fun afterTextChanged(s: Editable?) {
            when (requireView().findFocus()) {
                binding.etxListName -> isNameModified = true
                binding.etxListDescription -> isDescriptionModified = true
            }
            binding.btnCreateList.isEnabled =
                isNameModified && isDescriptionModified
        }
    }

    override fun initializeViews() {
        //init views here

        //Set rules
        binding.etxListName.setTag(com.indisparte.util.R.id.validation_rules, nameValidationRule)
        binding.etxListDescription.setTag(
            com.indisparte.util.R.id.validation_rules,
            nameValidationRule
        )

        binding.etxListName.addTextChangedListener(textWatcherToEnableSaveButton)
        binding.etxListDescription.addTextChangedListener(textWatcherToEnableSaveButton)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //use views here


        viewModel.listCreationStatus.collectIn(viewLifecycleOwner) { result ->
            result?.let {
                it.whenResources(
                    onSuccess = {
                        Toast.makeText(
                            requireContext(),
                            "Lista creata con successo!",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigateUp()
                    },
                    onError = { exception ->
                        LOG.e(exception)
                    },
                    onLoading = {
                        LOG.i("In attesa della creazione della lista...")
                    }
                )
            }
        }

        with(binding) {
            btnCreateList.setOnClickListener {
                if (validator.isValid()) {
                    val title = etxListName.text.toString()
                    val description = etxListDescription.text.toString()
                    viewModel.createList(
                        MediaList(
                            title = title,
                            description = description,
                            updateDate = System.currentTimeMillis().toString()
                        )
                    )
                }
            }

            closeIcon.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}