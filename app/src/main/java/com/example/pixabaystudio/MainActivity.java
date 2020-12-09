package com.example.pixabaystudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.pixabaystudio.images.ImageFragment;
import com.example.pixabaystudio.model.ImagesPojo;
import com.example.pixabaystudio.videos.VideoFragment;
import com.example.pixabaystudio.viewpager.OrderViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById( R.id.viewPager2 );
        viewPager2.setAdapter( new OrderViewPager( this ) );

        tabLayout = findViewById( R.id.tabLayout1 );
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText( "Images" );
                        tab.setIcon( R.drawable.ic_image_black_24dp );
                        break;
                    case 1:
                        tab.setText( "Videos" );
                        tab.setIcon( R.drawable.ic_video_library_black_24dp );
                        break;
                }
            }
        }
        );
        tabLayoutMediator.attach();
    }
}