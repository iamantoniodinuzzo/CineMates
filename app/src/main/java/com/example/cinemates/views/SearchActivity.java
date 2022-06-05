package com.example.cinemates.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import com.example.cinemates.databinding.ActivityMainBinding;
import com.example.cinemates.databinding.ActivitySearchBinding;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author Antonio Di Nuzzo
 * Created 05/06/2022 at 09:14
 */
@AndroidEntryPoint
public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding mBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}
