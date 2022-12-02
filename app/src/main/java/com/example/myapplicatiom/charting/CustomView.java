package com.example.myapplicatiom.charting;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplicatiom.R;
import com.example.myapplicatiom.databinding.CustomViewBinding;

public class CustomView extends ConstraintLayout {

    Drawable imgDrawable;
    String title, subtitle;
    CustomViewBinding binding;


    public CustomView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);


        binding.imageView.setImageDrawable(imgDrawable);
        binding.titleTextView.setText(title);
        binding.subtitleTextView.setText(subtitle);
    }


    private void init(Context context, AttributeSet attrs){

        binding = CustomViewBinding.inflate(LayoutInflater.from(context),this,true);

        //TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);

        imgDrawable = a.getDrawable(R.styleable.CustomView_setImageDrawable);
        title = a.getString(R.styleable.CustomView_setTitle);
        subtitle = a.getString(R.styleable.CustomView_setSubTitle);

        a.recycle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        binding.subtitleTextView.setText(title);
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String text) {
        binding.subtitleTextView.setText(text);
    }

    public Drawable getImgDrawable() {
        return imgDrawable;
    }

    public void setImgDrawable(Drawable imgDrawable) {
        this.imgDrawable = imgDrawable;
    }

}
