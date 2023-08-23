package com.indisparte.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragments: MutableList<Fragment> = mutableListOf()
    private val fragmentTitles: MutableList<String> = mutableListOf()


    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    /**
     * Remove a fragment from the adapter.
     * @param fragment The fragment to remove.
     */
    fun removeFragment(fragment: Fragment) {
        val position = fragments.indexOf(fragment)
        if (position != -1) {
            fragments.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    fun getPageTitle(position: Int): String {
        return fragmentTitles[position]
    }
}
