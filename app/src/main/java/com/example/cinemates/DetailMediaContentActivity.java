package com.example.cinemates;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemates.databinding.ActivityDetailMediaContentBinding;

public class DetailMediaContentActivity extends AppCompatActivity {

    private ActivityDetailMediaContentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailMediaContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }

}