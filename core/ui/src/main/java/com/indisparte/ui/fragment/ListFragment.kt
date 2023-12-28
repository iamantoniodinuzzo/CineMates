package com.indisparte.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.indisparte.network.exception.CineMatesException
import com.indisparte.ui.adapter.BaseAdapter
import com.indisparte.ui.databinding.FragmentListingItemsBinding

/**
 * A fragment in which a list of elements is shown.
 * @param T The type of objects contained in the recyclerview
 * @param A The adapter must extend [BaseAdapter]
 * @param adapter The adapter constructor
 * @author Antonio Di Nuzzo
 */
abstract class ListFragment<T, VB : ViewDataBinding, A : BaseAdapter<T, VB>>(
    val adapter: A,
) : BaseFragment<FragmentListingItemsBinding>() {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListingItemsBinding
        get() = FragmentListingItemsBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addItemsToTheAdapter()
        binding.recyclerView.adapter = adapter
    }

    /**
     * This method is called to initialize the list in the fragment.
     * Child classes must override this method to provide the data for the list and perform any additional initialization.
     * Use the [adapter] to submit the list
     */
    protected abstract fun addItemsToTheAdapter()

    protected fun showLoading() {
        binding.recyclerView.showLoading()
    }

    protected fun hideLoading() {
        binding.recyclerView.hideLoading()
    }

    protected fun showError(exception: CineMatesException) {
        hideLoading()
        binding.recyclerView.apply {
            val errorMessage = context.getString(exception.messageRes)
            setEmptyStateTitle(errorMessage)
            exception.drawableRes?.let {
                setEmptyStateImage(it)
            }
        }
    }

}