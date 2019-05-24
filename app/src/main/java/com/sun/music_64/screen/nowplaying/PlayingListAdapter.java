package com.sun.music_64.screen.nowplaying;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;

import java.util.Collections;
import java.util.List;

public class PlayingListAdapter extends RecyclerView.Adapter<PlayingListAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {
    private List<Track> mTracks;
    private Track mTrackPlaying;
    private TrackClickListener mListener;

    public PlayingListAdapter(List<Track> tracks, TrackClickListener listener) {
        mTracks = tracks;
        mListener = listener;
    }

    public void setTrackPlaying(Track trackPlaying) {
        mTrackPlaying = trackPlaying;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycler_playing_list, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        boolean isPlaying = mTracks.get(i).equals(mTrackPlaying);
        viewHolder.setStatePlaying(isPlaying);
        viewHolder.bindData(mTracks.get(i), ++i);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTracks, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemMoved() {
        notifyDataSetChanged();
    }

    interface TrackClickListener {
        void onTrackClick(Track track);

        void onRemoveTrackClick(Track track);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            ItemTouchHelperViewHolder {
        private TextView mTextName;
        private TextView mTextArtist;
        private ImageView mImagePlay;
        private Track mTrack;
        private TextView mTextCount;
        private boolean mIsPlaying;
        private TrackClickListener mListener;

        public ViewHolder(@NonNull View itemView, TrackClickListener listener) {
            super(itemView);
            initUi();
            mListener = listener;
        }

        private void initUi() {
            mTextName = itemView.findViewById(R.id.text_name);
            mTextArtist = itemView.findViewById(R.id.text_artist);
            mTextCount = itemView.findViewById(R.id.text_count);
            mImagePlay = itemView.findViewById(R.id.image_option);
        }

        public void bindData(Track track, int position) {
            mTrack = track;
            mTextName.setText(track.getTitle());
            mTextArtist.setText(track.getArtist());
            mTextCount.setText(String.valueOf(position));
            itemView.setOnClickListener(this);
            mImagePlay.setOnClickListener(this);
        }

        public void setStatePlaying(Boolean isPlaying) {
            mIsPlaying = isPlaying;
            Resources resources = itemView.getResources();
            if (isPlaying) {
                mTextName.setTextColor(resources.getColor(R.color.color_primary));
                mTextArtist.setTextColor(resources.getColor(R.color.color_primary));
                mTextCount.setTextColor(resources.getColor(R.color.color_primary));
                mImagePlay.setImageResource(R.drawable.wave);
            } else {
                mIsPlaying = false;
                mTextName.setTextColor(resources.getColor(R.color.color_black));
                mTextArtist.setTextColor(resources.getColor(R.color.color_black));
                mTextCount.setTextColor(resources.getColor(R.color.color_black));
                mImagePlay.setImageResource(R.drawable.ic_delete);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_option:
                    if (mIsPlaying) return;
                    else mListener.onRemoveTrackClick(mTrack);
                    break;
                default:
                    if (mIsPlaying) return;
                    mListener.onTrackClick(mTrack);
            }
        }

        @Override
        public void onItemSelected() {
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

}
