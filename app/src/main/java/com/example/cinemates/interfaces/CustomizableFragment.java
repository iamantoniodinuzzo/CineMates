package com.example.cinemates.interfaces;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Antonio Di Nuzzo
 * Created 18/12/2021 at 16:30
 */
public interface CustomizableFragment {

    void changeLayout(RecyclerView.LayoutManager layoutManager);

    void bindData(String query);

//    void filter();
}
