package com.example.pixabaystudio.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixabaystudio.R;
import com.example.pixabaystudio.model.VideosPojo;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter {

    List<VideosPojo> videosPojos;
    Context context;

    public VideosAdapter(List<VideosPojo> videosPojos, Context context) {
        this.videosPojos = videosPojos;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.video_list_item,parent,false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1=(ViewHolder) holder;
        VideosPojo pojo1=videosPojos.get( position );
        String tags = pojo1.getTags();
        String videourl = pojo1.getUrl();
        holder1.text1.setText( tags );
        holder1.intiPlayer( videourl );
//        if (holder1.isPlaying()) {
//            Log.e("TAG1", "play");
//            holder1.releasePlayer();
//            holder1.intiPlayer(videourl);
//        } else {
//            Log.e("TAG1", "empty");
//            holder1.intiPlayer(videourl);
//        }

    }

    @Override
    public int getItemCount() {
        return videosPojos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        SimpleExoPlayerView exoPlayerView;
        SimpleExoPlayer exoPlayer;
        private long playbackPosition;
        private int currentWindow;
        private boolean playWhenReady;
        TextView text1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById( R.id.textViewVideo );
            exoPlayerView=itemView.findViewById( R.id.videoViewExo );

        }
        private void intiPlayer(String url) {
            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
                Uri videoURI = Uri.parse(url);
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);
                exoPlayerView.setPlayer(exoPlayer);
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(false);
            } catch (Exception e) {
                Log.e("MainAcvtivity", " exoplayer error " + e.toString());
            }


        }

//        private boolean isPlaying() {
//            return exoPlayer != null
//                    && exoPlayer.getPlaybackState() != Player.STATE_ENDED
//                    && exoPlayer.getPlaybackState() != Player.STATE_IDLE
//                    && exoPlayer.getPlayWhenReady();
//        }
//
//        private void releasePlayer() {
//            if (exoPlayer != null) {
//                playbackPosition = exoPlayer.getCurrentPosition();
//                currentWindow = exoPlayer.getCurrentWindowIndex();
//                playWhenReady = exoPlayer.getPlayWhenReady();
//                exoPlayer.release();
//                exoPlayer = null;
//            }
//        }
    }
}