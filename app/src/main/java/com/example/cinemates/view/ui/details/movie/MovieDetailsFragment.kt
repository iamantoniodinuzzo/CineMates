package com.example.cinemates.view.ui.details.movie

import com.example.cinemates.view.ui.details.movie.MovieInfoFragment
import com.example.cinemates.view.ui.details.movie.MovieCastFragment
import com.example.cinemates.view.ui.details.movie.MovieImagesFragment
import com.example.cinemates.adapter.ViewPagerAdapter
import android.os.Bundle
import com.example.cinemates.view.ui.details.movie.MovieDetailsFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.example.cinemates.databinding.FragmentMovieDetailsBinding
import com.example.cinemates.model.data.Movie
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.google.android.material.tabs.TabLayout

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
class MovieDetailsFragment : Fragment() {
    private lateinit var mBinding: FragmentMovieDetailsBinding
    private lateinit var args: MovieDetailsFragmentArgs
    private lateinit var mMovieInfoFragment: MovieInfoFragment
    private lateinit var mMovieCastFragment: MovieCastFragment
    private lateinit var mMovieImagesFragment: MovieImagesFragment
    private lateinit var mViewPagerAdapter: ViewPagerAdapter
    private lateinit var mBundle: Bundle
    private val viewModel: MovieDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = MovieDetailsFragmentArgs.fromBundle(requireArguments())
        mViewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager, lifecycle)
        mMovieInfoFragment = MovieInfoFragment()
        mMovieCastFragment = MovieCastFragment()
        mMovieImagesFragment = MovieImagesFragment()
        mBundle = Bundle()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        viewModel.setSelectedMovie(args.movie)

        viewModel.selectedMovie.observe(viewLifecycleOwner){selectedMovie->
            mBinding.movie = selectedMovie
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mBinding.toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }

        initializeViewPager()

    }

    private fun initializeViewPager() {
        mViewPagerAdapter.addFragment(mMovieInfoFragment)
        mViewPagerAdapter.addFragment(mMovieCastFragment)
        mViewPagerAdapter.addFragment(mMovieImagesFragment)
        mBinding.viewPager.adapter = mViewPagerAdapter
        mBinding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Info"
                1 -> tab.text = "Cast"
                2 -> tab.text = "Images"
            }
        }.attach()
    }

}