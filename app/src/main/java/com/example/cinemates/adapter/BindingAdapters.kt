package com.example.cinemates.adapter

import android.text.Html
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cinemates.R
import com.example.cinemates.model.entities.Genre
import com.example.cinemates.model.entities.PersonalStatus
import com.example.cinemates.model.entities.ProductionCompany
import com.example.cinemates.model.entities.Section
import com.example.cinemates.util.IMAGE_BASE_URL_W500
import com.example.cinemates.util.IMAGE_BASE_URL_W780
import com.example.cinemates.util.LinearInfoView
import com.example.cinemates.util.YT_API_KEY
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener
import com.google.android.youtube.player.YouTubeThumbnailView
import java.text.NumberFormat
import java.util.*
import java.util.stream.Collectors

/**
 * @author Antonio Di Nuzzo
 * Created 20/05/2022 at 11:40
 */
const val TAG = "BindingAdapters"

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    //ImageView: Using Glide Library
    Glide.with(view.context)
        .load(
            "$IMAGE_BASE_URL_W500${url}"

        )
        .error(R.drawable.ic_outline_image_not_supported_24)
        .placeholder(R.drawable.ic_death_star)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(view)
}

@BindingAdapter("imageUrlAvatar")
fun loadAvatar(view: ImageView, url: String?) {
    //ImageView: Using Glide Library
    Glide.with(view.context)
        .load(
            "$IMAGE_BASE_URL_W500${url}"
        )
        .error(R.drawable.ic_avatar)
        .placeholder(R.drawable.ic_cap_america)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(view)
}

@BindingAdapter("imageUrlLong")
fun loadImageLong(view: ImageView, url: String?) {
    //ImageView: Using Glide Library
    Glide.with(view.context)
        .load(
            "$IMAGE_BASE_URL_W780${url}"
        )
        .error(R.drawable.ic_outline_image_not_supported_24)
        .placeholder(R.drawable.ic_avengers)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(view)

}
@BindingAdapter("isFavorite")
fun isFavorite(view: ImageButton, value: Boolean) {
    view.isPressed = value
}

@BindingAdapter("toSee")
fun setStatusToSee(view: ImageButton, value: PersonalStatus?) {
    if (value != null)
        view.isPressed = (value == PersonalStatus.TO_SEE)
    else
        view.isPressed = (value == PersonalStatus.EMPTY)
}

@BindingAdapter("seen")
fun setStatusSeen(view: ImageButton, value: PersonalStatus?) {
    if (value != null)
        view.isPressed = (value == PersonalStatus.SEEN)
    else
        view.isPressed = (value == PersonalStatus.EMPTY)
}



@BindingAdapter("runtime")
fun loadRuntime(view: TextView, runtime: Int?) {
    var value = ""
    if (runtime != null) {
        val hours = (runtime / 60) //since both are ints, you get an int
        val minutes = (runtime % 60)
        value = String.format("%d h %02d min", hours, minutes)
    }
    loadText(view, value)
}

@BindingAdapter("loadText")
fun loadText(view: TextView, value: String?) {
    //Two cases (1) empty string literal (2) empty string number
    if (value != null) {
        if (value.length > 3) {
            //ho usato il numero 3 per indicare il caso in cui buget o revenue
            //contengano "0 $", in questo caso si tratta di 3 caratteri che non voglio siano espressi
            view.text = value
        }
    } else {
        view.text = "Not specified"
    }
}

@BindingAdapter("value")
fun value(view: LinearInfoView, value: String?) {
    if (value != null) {
        view.value = value
    }
}

@InverseBindingAdapter(attribute = "value")
fun getValue(customField: LinearInfoView): String {
    return customField.value
}


@BindingAdapter("asHtml")
fun formatAsHtml(view: TextView, section: Section<*>) {
    val sectionTitle =
        "<font color=#FAFAFA><b>" + section.sectionName + "</b></font>"
    var sectionDescription = ""
    if (section.sectionContentDescription != null) {
        sectionDescription =
            " <font color=#3A55EA><small>" + section.sectionContentDescription + "</small></font>"
    }
    view.text = Html.fromHtml("$sectionTitle$sectionDescription", Html.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("currency")
fun loadBudget(view: TextView, budget: Int?) {
    val current = Locale.getDefault()
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency =
        Currency.getInstance(Currency.getInstance(current).currencyCode)
    loadText(view, format.format(budget))
}



@BindingAdapter("genres")
fun setGenresChip(chipGroup: ChipGroup, genres: List<Genre>?) {
    if (genres != null) {
        for ((id, name) in genres) {
            val chip = Chip(chipGroup.context)
            val drawable = ChipDrawable.createFromAttributes(
                chipGroup.context, null,
                0, R.style.Widget_MaterialComponents_Chip_Action
            )
            chip.setChipDrawable(drawable)
            chip.text = name
            chip.id = id
            chipGroup.addView(chip)
        }
    }
}

@BindingAdapter("production_companies")
fun setProductionCompanies(view: TextView, productionCompanies: ArrayList<ProductionCompany>?) {
    if (productionCompanies != null) {
        val result = ArrayList<String>()
        for ((_, _, name) in productionCompanies) {
            result.add(name)
        }
        view.text = result.toString()
    }
}

@BindingAdapter("loadOfficial")
fun setIsOfficial(textView: TextView, isOfficial: Boolean) {
    textView.text = if (isOfficial) "Official" else "Not Official"
}

@BindingAdapter("loadThumbnail")
fun loadThumbnail(youTubeThumbnailView: YouTubeThumbnailView, key: String?) {
    /*  initialize the thumbnail image view , we need to pass Developer Key */
    youTubeThumbnailView.initialize(
        YT_API_KEY,
        object : YouTubeThumbnailView.OnInitializedListener {
            override fun onInitializationSuccess(
                youTubeThumbnailView: YouTubeThumbnailView,
                youTubeThumbnailLoader: YouTubeThumbnailLoader
            ) {
                //when initialization is success, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo(key)
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                    OnThumbnailLoadedListener {
                    override fun onThumbnailLoaded(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        s: String
                    ) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release()
                    }

                    override fun onThumbnailError(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        errorReason: YouTubeThumbnailLoader.ErrorReason
                    ) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error")
                    }
                })
            }

            override fun onInitializationFailure(
                youTubeThumbnailView: YouTubeThumbnailView,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure")
            }
        })
}

@BindingAdapter("voteAverage")
fun loadVoteAverage(textView: TextView, number: Double) {
    textView.text = String.format("%,.1f", number)
}
