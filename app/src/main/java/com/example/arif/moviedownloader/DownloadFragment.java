package com.example.arif.moviedownloader;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class DownloadFragment extends Fragment {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd,mInterstitialAd1;
    private Button btnMale,btnFemale,btnAgeNext,btnNameNext,btnApp,btnWeb;
    private LinearLayout ageLayout,genderLay,nameLay,choiceLay,linkLay;




//    private OnFragmentInteractionListener mListener;

    public DownloadFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DownloadFragment newInstance(String param1, String param2) {
        DownloadFragment fragment = new DownloadFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        MobileAds.initialize(view.getContext(), getResources().getString(R.string.app_id));

        btnFemale = view.findViewById(R.id.female);
        btnMale = view.findViewById(R.id.male);
        btnAgeNext = view.findViewById(R.id.btnAgeNext);
        ageLayout = view.findViewById(R.id.ageLayout);
        genderLay = view.findViewById(R.id.genderLayout);
        nameLay = view.findViewById(R.id.nameLayout);
        choiceLay = view.findViewById(R.id.choiceLayout);
        linkLay = view.findViewById(R.id.movieLink);
        btnAgeNext = view.findViewById(R.id.btnAgeNext);
        btnNameNext = view.findViewById(R.id.btnNameNext);
        btnApp = view.findViewById(R.id.app);
        btnWeb = view.findViewById(R.id.web);

        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(view.getContext());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //Another loaded ad for ready to show
        mInterstitialAd1 = new InterstitialAd(view.getContext());
        mInterstitialAd1.setAdUnitId(getResources().getString(R.string.interstitial));
        mInterstitialAd1.loadAd(new AdRequest.Builder().build());

        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderClickEvent();
            }
        });
        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderClickEvent();
            }
        });
        btnAgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageNextClick();
            }
        });
        btnNameNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameNextClick();
            }
        });
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceClickEvent();
            }
        });
        btnApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceClickEvent();
            }
        });

        return view;
    }



    public void genderClickEvent(){

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            genderLay.setVisibility(View.INVISIBLE);
            ageLayout.setVisibility(View.VISIBLE);

        } else {
            Log.e("TAG", "The interstitial wasn't loaded yet.");
            Toast.makeText(getContext(),"Please wait a second and try again",Toast.LENGTH_LONG).show();
        }


    }
    public void ageNextClick(){
        if (mInterstitialAd1.isLoaded()) {
            mInterstitialAd1.show();
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            ageLayout.setVisibility(View.INVISIBLE);
            nameLay.setVisibility(View.VISIBLE);

        } else {
            Log.e("TAG", "The interstitial wasn't loaded yet.");
            Toast.makeText(getContext(),"Please wait a second and try again",Toast.LENGTH_LONG).show();
        }
    }
    public void nameNextClick(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd1.loadAd(new AdRequest.Builder().build());
            nameLay.setVisibility(View.INVISIBLE);
            choiceLay.setVisibility(View.VISIBLE);

        } else {
            Log.e("TAG", "The interstitial wasn't loaded yet.");
            Toast.makeText(getContext(),"Please wait a second and try again",Toast.LENGTH_LONG).show();
        }
    }
    public void choiceClickEvent(){
        if (mInterstitialAd1.isLoaded()){
            mInterstitialAd1.show();
            //mInterstitialAd.loadAd(new AdRequest.Builder().build());
            mInterstitialAd1.setAdListener(new AdListener(){
                @Override
                public void onAdClicked(){

                    choiceLay.setVisibility(View.INVISIBLE);
                    linkLay.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    Toast.makeText(getContext(),"Please wait a second and try again",Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
