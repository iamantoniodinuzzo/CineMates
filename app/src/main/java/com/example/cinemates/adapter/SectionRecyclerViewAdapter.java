package com.example.cinemates.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.SectionRowBinding;
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.example.cinemates.views.fragment.HomeFragmentDirections;
import com.example.cinemates.model.Section;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antonio Di Nuzzo
 * Created 15/12/2021 at 16:36
 */
public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {
    private List<Section> dataList = new ArrayList<>();

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SectionRowBinding sectionRowBinding = SectionRowBinding.inflate(layoutInflater, parent, false);
        return new SectionViewHolder(sectionRowBinding);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section section = dataList.get(position);
        holder.mBinding.setSection(section);
        holder.mBinding.executePendingBindings();
        List<Movie> movies_of_section = section.getSectionItems();

        SectionItemsRecyclerViewAdapter sectionItemsRecyclerViewAdapter = new SectionItemsRecyclerViewAdapter();
        sectionItemsRecyclerViewAdapter.addItems(movies_of_section);
        holder.mBinding.recyclerView.setAdapter(sectionItemsRecyclerViewAdapter);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addItems(List<Section> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    static class SectionViewHolder extends RecyclerViewEmptySupport.ViewHolder {
        SectionRowBinding mBinding;

        SectionViewHolder(@NonNull SectionRowBinding sectionRowBinding) {
            super(sectionRowBinding.getRoot());
            this.mBinding = sectionRowBinding;

            this.mBinding.actionOpenPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HomeFragmentDirections.ActionHomeFragmentToDetailedViewFragment action =
                                HomeFragmentDirections.actionHomeFragmentToDetailedViewFragment();
                    action.setSection(mBinding.textSectionTitle.getText().toString());
                    Navigation.findNavController(view).navigate(action);

                }
            });


        }


    }
}