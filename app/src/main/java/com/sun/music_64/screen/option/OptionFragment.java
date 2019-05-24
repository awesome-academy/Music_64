package com.sun.music_64.screen.option;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.service.PlayMusicService;

public class OptionFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private Track mTrack;
    private ImageView mImageArtwork;
    private static final String TITLE_TRACK = "TITLE_TRACK";
    private ImageView mImageFavourite;
    private TextView mTextTrack;
    private TextView mTextArtist;
    private TextView mTextFavourite;
    private TextView mTextPlayingList;
    private PlayMusicService mService;
    private ServiceConnection mConnection;
    private OptionPresenter mPresenter;

    public OptionFragment() {
    }

    public static OptionFragment getInstance(Track track) {
        OptionFragment fragment = new OptionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TITLE_TRACK, track);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_option, container, false);
        Bundle bundle = getArguments();
        mTrack = (Track) bundle.getSerializable(TITLE_TRACK);
        initUi(view);
        bindData();
        registerListener();
        return view;
    }

    @Override
    public void onDestroyView() {
        getActivity().unbindService(mConnection);
        super.onDestroyView();
    }

    private void registerListener() {
        mTextFavourite.setOnClickListener(this);
        mTextPlayingList.setOnClickListener(this);
    }

    private void bindData() {
        Glide.with(getContext())
                .load(mTrack.getArtworkUrl())
                .apply(new RequestOptions().transforms(new RoundedCorners(10)))
                .into(mImageArtwork);
        mTextTrack.setText(mTrack.getTitle());
        mTextArtist.setText(mTrack.getArtist());
    }

    private void initUi(View view) {
        mImageFavourite = view.findViewById(R.id.image_favorite);
        mImageArtwork = view.findViewById(R.id.image_artwork_option);
        mTextTrack = view.findViewById(R.id.text_title_option);
        mTextArtist = view.findViewById(R.id.text_artist_option);
        mTextFavourite = view.findViewById(R.id.text_favorite);
        mTextPlayingList = view.findViewById(R.id.text_playing_list);

        mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayMusicService.MyBinder myBinder = (PlayMusicService.MyBinder) service;
                mService = myBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        getActivity().bindService(PlayMusicService.getIntent(getContext()), mConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_favorite:
                break;
            case R.id.text_playing_list:
                mService.addTrack(mTrack);
                Toast.makeText(getContext(), getString(R.string.title_added), Toast.LENGTH_SHORT).show();
        }
    }
}
