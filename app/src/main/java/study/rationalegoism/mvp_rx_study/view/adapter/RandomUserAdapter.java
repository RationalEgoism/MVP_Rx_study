package study.rationalegoism.mvp_rx_study.view.adapter;

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

import study.rationalegoism.mvp_rx_study.MainActivity;
import study.rationalegoism.mvp_rx_study.R;
import study.rationalegoism.mvp_rx_study.domain.entity.Result;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>{


    private List<Result> resultList = new ArrayList<>();
    MainActivity mainActivity;

    public RandomUserAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

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
        holder.textViewCard.setText(result.getName().getFirst());
        Glide.with(mainActivity)
                .load(result.getPicture().getMedium())
                .into(holder.imageViewCard);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    public class RandomUserViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewCard;
        public TextView textViewCard;

        public RandomUserViewHolder(View itemView) {
            super(itemView);
            imageViewCard = itemView.findViewById(R.id.imageViewCard);
            textViewCard = itemView.findViewById(R.id.textViewCard);
        }
    }
}
