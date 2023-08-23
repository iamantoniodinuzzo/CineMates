package com.indisparte.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.home.databinding.ListItemSectionBinding
import com.indisparte.home.util.Section
import com.indisparte.model.entity.Movie
import com.indisparte.navigation.NavigationFlow
import com.indisparte.navigation.ToFlowNavigable
import com.indisparte.network.Result
import com.indisparte.ui.adapter.MovieAdapter
import com.indisparte.ui.adapter.OnItemClickListener
import com.indisparte.ui.adapter.PeopleAdapter
import com.indisparte.ui.adapter.TvShowAdapter

/**
 * Adapter class for displaying different sections in a RecyclerView.
 *
 * The adapter is responsible for inflating the appropriate view holders based on the section type,
 * binding the data to the view holders, and determining the view type for each section.
 *
 * @author Antonio Di Nuzzo
 */
class SectionAdapter(private val fragment: Fragment) :
    ListAdapter<Section, RecyclerView.ViewHolder>(SectionDiffCallback()) {

    /**
     * DiffUtil callback for calculating the difference between old and new sections.
     */
    private class SectionDiffCallback : DiffUtil.ItemCallback<Section>() {
        override fun areItemsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem.titleResId == newItem.titleResId
        }

        override fun areContentsTheSame(oldItem: Section, newItem: Section): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MOVIE_SECTION -> {
                val binding = ListItemSectionBinding.inflate(inflater, parent, false)
                MovieSectionViewHolder(binding)
            }

            VIEW_TYPE_TV_SHOW_SECTION -> {
                val binding = ListItemSectionBinding.inflate(inflater, parent, false)
                TvShowSectionViewHolder(binding)
            }

            VIEW_TYPE_PEOPLE_SECTION -> {
                val binding = ListItemSectionBinding.inflate(inflater, parent, false)
                PeopleSectionViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val section = getItem(position)
        when (holder) {
            is MovieSectionViewHolder -> {
                val movieSection = section as Section.MovieSection
                holder.bind(movieSection)
            }

            is TvShowSectionViewHolder -> {
                val tvShowSection = section as Section.TvShowSection
                holder.bind(tvShowSection)
            }

            is PeopleSectionViewHolder -> {
                val peopleSection = section as Section.PeopleSection
                holder.bind(peopleSection)
            }
        }
    }

    fun submitSectionList(sections: List<Section>) {
        submitList(sections.toMutableList())
    }

    override fun getItemViewType(position: Int): Int {
        val section = getItem(position)
        return when (section) {
            is Section.MovieSection -> VIEW_TYPE_MOVIE_SECTION
            is Section.TvShowSection -> VIEW_TYPE_TV_SHOW_SECTION
            is Section.PeopleSection -> VIEW_TYPE_PEOPLE_SECTION
        }
    }


    // ViewHolder per MovieSection
    private inner class MovieSectionViewHolder(private val binding: ListItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieSection: Section.MovieSection) {
            val context = binding.root.context
            binding.apply {
                textSectionTitle.text = context.getString(movieSection.titleResId)
                recyclerView.apply {
                    val movieAdapter = MovieAdapter()
                    movieAdapter.setOnItemClickListener(object : OnItemClickListener<Movie> {
                        override fun onItemClick(item: Movie) {
                            val activity = fragment.requireActivity()
                            if (activity is ToFlowNavigable) {
                                activity.navigateToFlow(NavigationFlow.MovieDetailsFlow(item.id))
                            }
                        }

                    })
                    adapter = movieAdapter
                    when (movieSection.movies) {
                        is Result.Error -> {
                            recyclerView.hideLoading()
                            val errorMessage = movieSection.movies.exception.message
                            this.setEmptyStateTitle("Error")
                            this.setEmptyStateSubtitle(errorMessage)
                        }

                        is Result.Loading -> {
                            recyclerView.showLoading()
                        }

                        is Result.Success -> {
                            recyclerView.hideLoading()
                            movieAdapter.submitList(movieSection.movies.data)
                        }

                    }
                }
            }

        }
    }

    // ViewHolder per TvShowSection
    private inner class TvShowSectionViewHolder(private val binding: ListItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShowSection: Section.TvShowSection) {
            val context = binding.root.context
            binding.apply {
                textSectionTitle.text = context.getString(tvShowSection.titleResId)
                recyclerView.apply {
                    val tvShowAdapter = TvShowAdapter()
                    adapter = tvShowAdapter
                    when (tvShowSection.tvShows) {
                        is Result.Error -> {
                            recyclerView.hideLoading()
                            val errorMessage = tvShowSection.tvShows.exception.message
                            this.setEmptyStateTitle("Error")//TODO string res
                            this.setEmptyStateSubtitle(errorMessage)

                        }

                        is Result.Loading -> {
                            recyclerView.showLoading()
                        }

                        is Result.Success -> {
                            recyclerView.hideLoading()
                            tvShowAdapter.submitList(tvShowSection.tvShows.data)
                        }

                    }

                }
            }
        }
    }

    // ViewHolder per PeopleSection
    private inner class PeopleSectionViewHolder(private val binding: ListItemSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(peopleSection: Section.PeopleSection) {
            val context = binding.root.context
            binding.apply {
                textSectionTitle.text = context.getString(peopleSection.titleResId)
                recyclerView.apply {
                    val peopleAdapter = PeopleAdapter()
                    adapter = peopleAdapter
                    when (peopleSection.people) {
                        is Result.Error -> {
                            recyclerView.hideLoading()
                            val errorMessage = peopleSection.people.exception.message
                            this.setEmptyStateTitle("Error")//TODO add to string res
                            this.setEmptyStateSubtitle(
                                errorMessage
                            )

                        }

                        is Result.Loading -> {
                            recyclerView.showLoading()
                        }

                        is Result.Success -> {
                            recyclerView.hideLoading()
                            peopleAdapter.submitList(peopleSection.people.data)
                        }

                    }

                }
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_MOVIE_SECTION = 0
        private const val VIEW_TYPE_TV_SHOW_SECTION = 1
        private const val VIEW_TYPE_PEOPLE_SECTION = 2
    }

}



