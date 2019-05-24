package com.sun.music_64.screen.user.musicoffline;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;
import com.sun.music_64.screen.genrescreen.ItemClickRecyclerView;

import java.io.File;
import java.util.List;

public class MusicOffAdapter extends RecyclerView.Adapter<MusicOffAdapter.MyViewHolder> {

    private List<Track> mtracks;
    private ItemClickRecyclerView mItemClickRecyclerView;

    public MusicOffAdapter(List<Track> tracks, ItemClickRecyclerView itemClickRecyclerView) {
        mtracks = tracks;
        mItemClickRecyclerView = itemClickRecyclerView;
    }

    @NonNull
    @Override
    public MusicOffAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.musicoff_item_list, viewGroup, false);
        MusicOffAdapter.MyViewHolder myViewHolder = new MusicOffAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicOffAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.setOnItenClickListener(mItemClickRecyclerView);
        viewHolder.bindData(mtracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mtracks == null ? 0 : mtracks.size();
    }

    public void setData(List<Track> tracks) {
        mtracks.clear();
        mtracks.addAll(tracks);
        notifyDataSetChanged();
    }

    public List<Track> gettracks() {
        return mtracks;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextSongName;
        private TextView mTextAuthName;
        private ImageView mImageShow;
        private ItemClickRecyclerView mItemClickRecyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_music_off_song_name);
            mTextAuthName = itemView.findViewById(R.id.text_music_off_auth_name);
            mImageShow = itemView.findViewById(R.id.image_music_off_show_infor);
            itemView.setOnClickListener(this);
            mImageShow.setOnClickListener(this);
        }

        public void bindData(Track track) {
            mTextSongName.setText(track.getTitle());
            mTextAuthName.setText(track.getArtist());
        }

        void setOnItenClickListener(ItemClickRecyclerView itemClickListener) {
            this.mItemClickRecyclerView = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_music_off_show_infor:
                    mItemClickRecyclerView.onShow(itemView, getAdapterPosition());
                default:
                    mItemClickRecyclerView.onClick(itemView, getLayoutPosition());
            }
        }
    }
}
