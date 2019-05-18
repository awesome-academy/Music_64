package com.sun.music_64.screen.genrescreen;

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

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Track> mtracks;
    private ItemClickRecyclerView mItemClickRecyclerView;

    public GenreAdapter(List<Track> tracks, ItemClickRecyclerView itemClickRecyclerView) {
        mtracks = tracks;
        mItemClickRecyclerView = itemClickRecyclerView;
    }

    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.genre_item_list, viewGroup, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GenreAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setOnItenClickListener(mItemClickRecyclerView);
        viewHolder.bindData(i, mtracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mtracks == null ? 0 : mtracks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextSongName;
        private TextView mTextAuthName;
        private TextView mTextPosition;
        private ImageButton mImageShow;
        private ItemClickRecyclerView mItemClickRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_song_name);
            mTextAuthName = itemView.findViewById(R.id.text_auth_name);
            mTextPosition = itemView.findViewById(R.id.text_index);
            mImageShow = itemView.findViewById(R.id.image_show_infor);
            itemView.setOnClickListener(this);
            mImageShow.setOnClickListener(this);
        }

        public void bindData(int position, Track track) {
            mTextSongName.setText(track.getTitle());
            mTextPosition.setText(String.valueOf(++position));
            mTextAuthName.setText(track.getArtist());
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
