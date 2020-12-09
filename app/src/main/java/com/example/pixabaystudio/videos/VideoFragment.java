package com.example.pixabaystudio.videos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pixabaystudio.R;
import com.example.pixabaystudio.adapter.VideosAdapter;
import com.example.pixabaystudio.model.VideosPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    RequestQueue requestQueue;
    List<VideosPojo> videosPojos = new ArrayList<>();
    RecyclerView recyclerView;


    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView=view.findViewById( R.id.recyclerVideos );
        recyclerView.setLayoutManager( new LinearLayoutManager( view.getContext() ) );
        requestQueue = Volley.newRequestQueue( view.getContext() );
        String URL = "https://pixabay.com/api/videos/?key=17829037-929c36dafe663df9cfc1d7793&q=yellow+flowers";
        JsonObjectRequest request = new JsonObjectRequest( Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                JSONObject h1=hit.getJSONObject( "videos" );
                                JSONObject h2=h1.getJSONObject( "large" );
                                String tags=hit.getString("tags");
                                String webformatURL = h2.getString("url");
                                videosPojos.add(new VideosPojo(tags,webformatURL));
                            }
//                            recyclerView.setAdapter(new VideosAdapter(videosPojos));
                            recyclerView.setAdapter( new VideosAdapter( videosPojos,getActivity() ) );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        return  view;
    }
}
