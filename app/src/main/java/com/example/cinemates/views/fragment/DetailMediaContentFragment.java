package com.example.cinemates.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cinemates.adapter.ViewPagerAdapter;
import com.example.cinemates.databinding.FragmentDetailMediaContentBinding;
import com.example.cinemates.model.Movie;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 26/05/2022 at 15:44
 */
@AndroidEntryPoint
public class DetailMediaContentFragment extends Fragment {
    public static FragmentManager fragmentManager;
    private FragmentDetailMediaContentBinding mBinding;
    private DetailMediaContentFragmentArgs args;
    private Movie mMovie;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        args = DetailMediaContentFragmentArgs.fromBundle(requireArguments());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDetailMediaContentBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovie = args.getMovie();
        mBinding.setMovie(mMovie);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        bindViewPagerAdapter(mBinding.viewPager);
        new TabLayoutMediator(mBinding.tabLayout, mBinding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Info");
                        break;
                    case 1:
                        tab.setText("Cast");
                        break;
                    case 2:
                        tab.setText("Images");
                        break;
                }
            }
        }).attach();
    }


    public void bindViewPagerAdapter(final ViewPager2 view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", mMovie);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), getLifecycle());
        final MediaInfoFragment mediaInfoFragment = new MediaInfoFragment();
        mediaInfoFragment.setArguments(bundle);
        final MediaCastFragment mediaCastFragment = new MediaCastFragment();
        mediaCastFragment.setArguments(bundle);
        final MediaImagesFragment mediaImagesFragment = new MediaImagesFragment();
        mediaImagesFragment.setArguments(bundle);

        adapter.addFragment(mediaInfoFragment);
        adapter.addFragment(mediaCastFragment);
        adapter.addFragment(mediaImagesFragment);

        view.setAdapter(adapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
