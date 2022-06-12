package com.example.cinemates.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.cinemates.R;
import com.example.cinemates.model.Genre;
import com.example.cinemates.model.ProductionCompany;
import com.example.cinemates.util.Constants;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Antonio Di Nuzzo
 * Created 20/05/2022 at 11:40
 */
public class BindingAdapters {

    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        //ImageView: Using Glide Library
        Glide.with(view.getContext())
                .load(Constants.ImageBaseURL
                        + url)
                .error(R.drawable.ic_round_error_outline_24)
                .centerCrop()
                .into(view);

    }

    @BindingAdapter({"imageUrlLong"})
    public static void loadImageLong(ImageView view, String url) {
        //ImageView: Using Glide Library
        Glide.with(view.getContext())
                .load(Constants.ImageBaseURLw780
                        + url)
                .error(R.drawable.ic_round_error_outline_24)
                .centerCrop()
                .into(view);
    }


    @BindingAdapter({"description"})
    public static void loadDescription(TextView view, String text) {
        if (TextUtils.isEmpty(text)) {// movie description is empty
            view.setText("Nessuna descrizione");
        } else {
            view.setText(text);
        }
    }

    @BindingAdapter({"runtime"})
    public static void loadRuntime(TextView view, long runtime) {
        int hours = (int) (runtime / 60); //since both are ints, you get an int
        int minutes = (int) (runtime % 60);
        view.setText(String.format("%d h %02d min", hours, minutes));
    }

    @BindingAdapter({"currency"})
    public static void loadBudget(TextView view, long budget) {
        Locale current = Locale.getDefault();
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance(Currency.getInstance(current).getCurrencyCode()));
        if (budget > 0)
            view.setText(format.format(budget));
        else
            view.setText("Non specificato");
    }

   @BindingAdapter("knowAs")
   public static void setKnownAs(TextView view, String[] names){
        try{
            view.setText(Arrays.stream(names).collect(Collectors.joining(" - ")));
        }catch (NullPointerException e){
            view.setText("Not specified");
        }
   }

    @BindingAdapter({"genres"})
    public static void setGenresChip(ChipGroup chipGroup, @NonNull ArrayList<Genre> genres) {
        if (genres != null) {
            for (Genre genre : genres) {

                Chip chip = new Chip(chipGroup.getContext());
                ChipDrawable drawable = ChipDrawable.createFromAttributes(chipGroup.getContext(), null,
                        0, R.style.Widget_MaterialComponents_Chip_Action);
                chip.setChipDrawable(drawable);
                chip.setText(genre.getName());
                chip.setId(genre.getId());
                chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Toast.makeText(chipGroup.getContext(), compoundButton.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                chipGroup.addView(chip);
            }
        }
    }

    @BindingAdapter({"production_companies"})
    public static void setProductionCompanies(TextView view, ArrayList<ProductionCompany> productionCompanies) {
        if (productionCompanies != null) {
            ArrayList<String> result = new ArrayList<>();
            for (ProductionCompany pc : productionCompanies) {
                result.add(pc.getName());
            }
            view.setText(result.toString());
        }
    }

    @BindingAdapter({"loadOfficial"})
    public static void setIsOfficial(TextView textView, boolean isOfficial) {
        textView.setText(isOfficial ? "Official" : "Not Official");
    }

}
