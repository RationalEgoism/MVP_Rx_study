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
import study.rationalegoism.mvp_rx_study.data.model.Person;

public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.RandomUserViewHolder>{
    public class RandomUserViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ivCardPicture) ImageView ivCardPicture;
        @BindView(R.id.tvCardName) TextView tvCardName;
        @BindView(R.id.tvCardPhone) TextView tvCardPhone;

        RandomUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<Person> personList = new ArrayList<>();

    @NonNull
    @Override
    public RandomUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.random_peoples_item,
                parent, false);
        return new RandomUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomUserViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.tvCardName.setText(person.getName().getFirst());
        holder.tvCardPhone.setText(person.getPhone());
        Glide.with(holder.ivCardPicture)
                .load(person.getPicture().getMedium())
                .into(holder.ivCardPicture);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public void clearData(){
        personList.clear();
        notifyDataSetChanged();
    }
}
