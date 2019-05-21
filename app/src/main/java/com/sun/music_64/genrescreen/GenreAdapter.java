package com.sun.music_64.genrescreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.MyViewHolder> {

    private Context mContext;
    private List<Track> mTracks;
    private ItemClickRecyclerView mItemClickRecyclerView;

    public GenreAdapter(Context context, List<Track> tracks, ItemClickRecyclerView itemClickRecyclerView) {
        mContext = context;
        mTracks = tracks;
        mItemClickRecyclerView = itemClickRecyclerView;
    }

    @NonNull
    @Override
    public GenreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.genre_item_list, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.setOnItenClickListener(mItemClickRecyclerView);
        viewHolder.bindData(mTracks.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextSongName;
        private TextView mTextAuthName;
        private TextView mTextPosition;
        private ImageButton mImageShow;
        private ItemClickRecyclerView mItemClickRecyclerView;


        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_song_name);
            mTextAuthName = itemView.findViewById(R.id.text_auth_name);
            mTextPosition = itemView.findViewById(R.id.text_index);
            mImageShow = itemView.findViewById(R.id.image_show_infor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickRecyclerView != null) {
                        mItemClickRecyclerView.onClick(itemView, getLayoutPosition());
                    }
                }
            });
            mImageShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickRecyclerView != null) {
                        mItemClickRecyclerView.onShow(itemView, getLayoutPosition());
                    }
                }
            });
        }

        void setOnItenClickListener(ItemClickRecyclerView itemClickListener) {
            this.mItemClickRecyclerView = itemClickListener;
        }

        public void bindData(Track track, int position) {
            mTextSongName.setText(track.getTitle());
            mTextPosition.setText(String.valueOf(position));
            mTextAuthName.setText(track.getArtist());
        }
    }
}
