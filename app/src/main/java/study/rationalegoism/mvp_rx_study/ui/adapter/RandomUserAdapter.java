package study.rationalegoism.mvp_rx_study.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import study.rationalegoism.mvp_rx_study.R;
import study.rationalegoism.mvp_rx_study.model.domain.entity.Result;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>{
    public class RandomUserViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivCardPicture) ImageView ivCardPicture;
        @BindView(R.id.tvCardName) TextView tvCardName;
        @BindView(R.id.tvCardPhone) TextView tvCardPhone;

        public RandomUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Result> resultList = new ArrayList<>();

    @NonNull
    @Override
    public RandomUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_peoples_item,
                parent, false);
        return new RandomUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.tvCardName.setText(result.getName().getFirst());
        holder.tvCardPhone.setText(result.getPhone());
        Glide.with(holder.ivCardPicture)
                .load(result.getPicture().getMedium())
                .into(holder.ivCardPicture);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }
}
