package com.example.mrad.demomaterial;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment {


    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    RecyclerView recyclerView;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstance;
    public static final String P_FILE_NAME = "UserData";
    public static final String USER_LEARNED = "UserLearned";

    adAdapter adAdapter;

    public NavigationDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        adAdapter=new adAdapter(getActivity(),getData());
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerList);

        recyclerView.setAdapter(adAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RequestQueue requestQueue= VolleySingleton.getvInstance().getRequestQueue();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://php.net/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), "Response: "+response, Toast.LENGTH_SHORT).show();

                Log.d(TAG, "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error : "+error, Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
        return view;
    }

    public static List<SingleRow> getData() {
        List<SingleRow> data = new ArrayList<>();

        int icons[] = {R.drawable.meme1, R.drawable.meme2, R.drawable.meme3, R.drawable.meme4, R.drawable.meme5};

        String[] titles = {"This is 1", "This is 2", "This is 3", "This is 4", "This is 5"};

        for (int i = 0; i < icons.length && i < titles.length; i++) {
            SingleRow current = new SingleRow();
            current.imageID = icons[i];
            current.title = titles[i];
            data.add(current);

        }

        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

        mDrawerLayout = drawerLayout;
        View containerView;
        containerView = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveTopreference(getActivity(), USER_LEARNED, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstance) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                mDrawerToggle.syncState();
            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFrompreference(getActivity(), USER_LEARNED, "false"));

        if (savedInstanceState != null) {
            mFromSavedInstance = true;

        }

    }

    public static void saveTopreference(Context context, String pName, String pValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(P_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(pName, pValue);
        editor.apply();
    }

    public static String readFrompreference(Context context, String pName, String pValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(P_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(pName, pValue);
    }

}
