package com.indisparte.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.indisparte.home.databinding.ListItemSectionBinding
import com.indisparte.home.util.Section
import com.indisparte.network.Resource

/**
 * Adapter class for displaying different sections in a RecyclerView.
 *
 * The adapter is responsible for inflating the appropriate view holders based on the section type,
 * binding the data to the view holders, and determining the view type for each section.
 *
 * @author Antonio Di Nuzzo (Indisparte)
 */
class SectionAdapter :
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

    fun submitList(sections: List<Section>) {
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
                    adapter = movieAdapter
                    when (movieSection.movies) {
                        is Resource.Error -> {
                            progress.hide()
                            this.setEmptyStateTitle("Error")
                            movieSection.movies.error?.localizedMessage?.let {
                                this.setEmptyStateSubtitle(
                                    it
                                )
                            }
                        }

                        is Resource.Loading -> {progress.show()}
                        is Resource.Success -> {
                            progress.hide()
                            movieAdapter.submitList(movieSection.movies.data)
                        }

                        null -> TODO()
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
                        is Resource.Error -> {
                            progress.hide()
                            this.setEmptyStateTitle("Error")
                            tvShowSection.tvShows.error?.localizedMessage?.let {
                                this.setEmptyStateSubtitle(
                                    it
                                )
                            }
                        }
                        is Resource.Loading -> {progress.show()}
                        is Resource.Success -> {
                            progress.hide()
                            tvShowAdapter.submitList(tvShowSection.tvShows.data)
                        }

                        null -> TODO()
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
                        is Resource.Error -> {
                            progress.hide()
                            this.setEmptyStateTitle("Error")
                            peopleSection.people.error?.localizedMessage?.let {
                                this.setEmptyStateSubtitle(
                                    it
                                )
                            }
                        }

                        is Resource.Loading -> {progress.show()}
                        is Resource.Success -> {
                            progress.hide()
                            peopleAdapter.submitList(peopleSection.people.data)
                        }

                        null -> TODO()
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



