package com.example.pixabaystudio.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pixabaystudio.images.ImageFragment;
import com.example.pixabaystudio.videos.VideoFragment;

public class OrderViewPager extends FragmentStateAdapter {
    public OrderViewPager(@NonNull FragmentActivity fragmentActivity) {
        super( fragmentActivity );
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ImageFragment();
            default:
                return new VideoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
