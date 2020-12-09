package com.example.pixabaystudio.images;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
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
import com.example.pixabaystudio.adapter.ImagesAdapter;
import com.example.pixabaystudio.model.ImagesPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {
    String URL = "https://pixabay.com/api/?key=17829037-929c36dafe663df9cfc1d7793&q=yellow+flowers&image_type=photo";
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    List<ImagesPojo> imagesPojos = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    public ImageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView = view.findViewById( R.id.recyclerImages );
        recyclerView.setLayoutManager( new LinearLayoutManager( view.getContext() ) );
        swipeRefreshLayout=view.findViewById( R.id.swipe );
        requestQueue = Volley.newRequestQueue( view.getContext() );
        getDataVolley();
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing( false );
                getDataVolley();
            }
        } );
        return view;
    }

    private void getDataVolley() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String tags=hit.getString("tags");
                                String webformatURL = hit.getString("webformatURL");
                                imagesPojos.add(new ImagesPojo(tags,webformatURL));
                            }
                            recyclerView.setAdapter(new ImagesAdapter(imagesPojos));
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
    }
}
