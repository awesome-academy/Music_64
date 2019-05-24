package com.sun.music_64.screen.user.favorite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sun.music_64.R;
import com.sun.music_64.data.model.Track;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<Track> mtracks;
    private ItemclickRecyclerFavorite mItemClickRecyclerView;

    public FavoriteAdapter(List<Track> tracks, ItemclickRecyclerFavorite itemClickRecyclerView) {
        mtracks = tracks;
        mItemClickRecyclerView = itemClickRecyclerView;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_item_list, viewGroup, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setOnItenClickListener(mItemClickRecyclerView);
        viewHolder.bindData(i, mtracks.get(i));
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextSongName;
        private TextView mTextAuthName;
        private TextView mTextPosition;
        private ImageButton mImageShow;
        private ImageButton mImageHeart;
        private ItemclickRecyclerFavorite mItemClickRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSongName = itemView.findViewById(R.id.text_favorite_song_name);
            mTextAuthName = itemView.findViewById(R.id.text_favorite_auth_name);
            mTextPosition = itemView.findViewById(R.id.text_favorite_index);
            mImageShow = itemView.findViewById(R.id.image_favorite_show_infor);
            mImageHeart = itemView.findViewById(R.id.image_favorite_heart);
            itemView.setOnClickListener(this);
            mImageShow.setOnClickListener(this);
            mImageHeart.setOnClickListener(this);
        }

        public void bindData(int position, Track track) {
            mTextSongName.setText(track.getTitle());
            mTextPosition.setText(String.valueOf(++position));
            mTextAuthName.setText(track.getArtist());
        }

        void setOnItenClickListener(ItemclickRecyclerFavorite itemClickListener) {
            this.mItemClickRecyclerView = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_favorite_show_infor:
                    mItemClickRecyclerView.onShow(itemView, getLayoutPosition());
                    break;
                case R.id.image_favorite_heart:
                    mItemClickRecyclerView.onHeartClick(itemView, getLayoutPosition());
                    break;
                default:
                    mItemClickRecyclerView.onClick(itemView, getLayoutPosition());
                    break;
            }
        }
    }
}
