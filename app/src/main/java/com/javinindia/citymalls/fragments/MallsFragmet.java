package com.javinindia.citymalls.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.javinindia.citymalls.R;
import com.javinindia.citymalls.apiparsing.CountryModel;
import com.javinindia.citymalls.constant.Constants;
import com.javinindia.citymalls.recyclerview.MallAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ashish on 08-09-2016.
 */
public class MallsFragmet extends BaseFragment implements TextWatcher, View.OnClickListener, MallAdapter.MyClickListener {

    private RecyclerView recyclerview;
    private List<CountryModel> mCountryModel;
    private MallAdapter adapter;
   // private AppCompatEditText appCompatEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity.getSupportActionBar().show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initialize(view);
        setRequest();
        return view;
    }

    private void setRequest() {
        String[] locales = Locale.getISOCountries();
        mCountryModel = new ArrayList<>();

        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            mCountryModel.add(new CountryModel(obj.getDisplayCountry(), obj.getISO3Country()));
        }

        adapter = new MallAdapter(mCountryModel,activity);
        adapter.setMyClickListener(MallsFragmet.this);
        recyclerview.setAdapter(adapter);
    }

    private void initialize(View view) {
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        RelativeLayout rlFavourateMalls = (RelativeLayout)view.findViewById(R.id.rlFavourateMalls);
        RelativeLayout rlOffers = (RelativeLayout)view.findViewById(R.id.rlOffers);
        RelativeLayout rlEvents = (RelativeLayout)view.findViewById(R.id.rlEvents);
        RelativeLayout rlSearch = (RelativeLayout)view.findViewById(R.id.rlSearch);
        rlFavourateMalls.setBackgroundColor(Color.parseColor("#000000"));
        rlFavourateMalls.setOnClickListener(this);
        rlOffers.setOnClickListener(this);
        rlEvents.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
      //  appCompatEditText = (AppCompatEditText) view.findViewById(R.id.etName);
     //   appCompatEditText.addTextChangedListener(this);


    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.offer_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlFavourateMalls:

                break;
            case R.id.rlOffers:

                break;
            case R.id.rlEvents:

                break;
            case R.id.rlSearch:

                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String text = s.toString().toLowerCase(Locale.getDefault());
        adapter.filter(text);
    }

    @Override
    public void onItemClick(int position, CountryModel model) {
        String name = model.getName();

        BaseFragment fragment = new OfferPostFragment();
        Bundle bundle = new Bundle();
        //    bundle.putSerializable("images", postImage);
        bundle.putInt("position", 0);
        bundle.putString("mallname","LBC,Ambience,Gurgaon");
        bundle.putString("offer","20% off at LBC");
        bundle.putString("timer","08 Sep 2016 – 18 Sep 2016");
        bundle.putString("discription","A shopping mall is a modern, chiefly North American, term for a form of shopping precinct or shopping center, in which one or more buildings form a complex of shops representing merchandisers with interconnecting walkways that enable customers to walk from unit to unit. A shopping arcade is a specific form serving the same purpose.");
        fragment.setArguments(bundle);
        callFragmentMethod(fragment, Constants.LOGIN_AND_SIGN_UP_TAG,R.id.navigationContainer);


    }
}
