package com.a92outlook.syedadil786.darood;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {


    static int [] mResources = {
            R.drawable.startup,
            R.drawable.darood1,
            R.drawable.darood2,
            R.drawable.darood3
    };
    MyFragmentAdapter mAdapter;
    ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else {
            Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
        }

        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    public class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // This method returns the fragment associated with the specified position.
        //
        // It is called when the Adapter needs a fragment and it does not exists.
        public Fragment getItem(int position) {

            Log.d("Position Fragment ", ""+position);
            // Create fragment object
            Fragment fragment = new DataFragment();
            // Attach some data to it that we'll use to populate our fragment layouts
            Bundle args = new Bundle();
            args.putInt("page_position", position);

            // Set the arguments on the fragment that will be fetched in DataFragment@onCreateView
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount() {
            return 4;
        }
    }

    public static class DataFragment extends Fragment {

        public DataFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout resource that'll be returned
            View rootView = inflater.inflate(R.layout.fragmentdemo, container, false);

            // Get the arguments that was supplied when the fragment was instantiated in the CustomPagerAdapter
            Bundle args = getArguments();
            int position = args.getInt("page_position");
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(mResources[position]);
            return rootView;
        }
    }



}





