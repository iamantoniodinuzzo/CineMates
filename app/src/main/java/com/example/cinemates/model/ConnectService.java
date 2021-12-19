package com.example.cinemates.model;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;

/**
 * @author Antonio Di Nuzzo
 * Created 19/12/2021 at 08:29
 */
public class ConnectService {
    private String text;
    private int drawable;
    private int action;

    public ConnectService(@NonNull String text, @NonNull int drawable, @NonNull int action) {
        this.text = text;
        this.drawable = drawable;
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void onClickItem(View view) {
// TODO        Navigation.findNavController(view).navigate(action);
        Toast.makeText(view.getContext(), "Cliccato: "+text, Toast.LENGTH_SHORT).show();
    }


}
