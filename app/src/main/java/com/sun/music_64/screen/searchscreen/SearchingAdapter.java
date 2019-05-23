package com.sun.music_64.screen.searchscreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.screen.genrescreen.ItemClickRecyclerView;

import java.util.List;

public class SearchingAdapter extends RecyclerView.Adapter<SearchingAdapter.ViewHolder> {

    private List<Track> mtracks;
    private ItemClickRecyclerView mItemClickRecyclerView;

    public SearchingAdapter(List<Track> tracks, ItemClickRecyclerView itemClickRecyclerView) {
        this.mtracks = tracks;
        this.mItemClickRecyclerView = itemClickRecyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.searching_item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setOnItenClickListener(mItemClickRecyclerView);
        viewHolder.bindData(mtracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mtracks == null ? 0 : mtracks.size();
    }

    public void getData(List<Track> tracks) {
        mtracks.clear();
        mtracks.addAll(tracks);
        notifyDataSetChanged();
    }

    public void resetTrack() {
        mtracks.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextSongName;
        private TextView mTextAuthName;
        private ImageView mImageSong;
        private ImageButton mImageShow;
        private ItemClickRecyclerView mItemClickRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_search_song_name);
            mTextAuthName = itemView.findViewById(R.id.text_search_auth_name);
            mImageSong = itemView.findViewById(R.id.image_search_song_image);
            mImageShow = itemView.findViewById(R.id.image_search_show_infor);
            itemView.setOnClickListener(this);
            mImageShow.setOnClickListener(this);
        }

        public void bindData(Track track) {
            mTextSongName.setText(track.getTitle());
            mTextAuthName.setText(track.getArtist());
            Glide.with(itemView)
                    .load(track.getArtworkUrl())
                    .into(mImageSong);
        }

        void setOnItenClickListener(ItemClickRecyclerView itemClickListener) {
            this.mItemClickRecyclerView = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_show_infor:
                    mItemClickRecyclerView.onShow(itemView, getLayoutPosition());
                    break;
                default:
                    mItemClickRecyclerView.onClick(itemView, getLayoutPosition());
                    break;
            }
        }
    }
}
