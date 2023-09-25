package com.example.fit_app_bachelor.ui.home.service;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeAdapter;
import com.example.fit_app_bachelor.ui.home.StagesActivity;
import com.example.fit_app_bachelor.ui.home.model.Stage;
import com.example.fit_app_bachelor.ui.home.model.Training;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.TrainingViewHolder> {
    private List<Training> trainings;

    public TrainingsAdapter(List<Training> trainings) {
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trening_card_item,parent,false);
        return new TrainingViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Training training = trainings.get(position);
        Context context = holder.itemView.getContext();
        holder.bindData(training,context);
    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public void onTrainingClick(int position, View view) {
        Training training = trainings.get(position);
        List<Stage> stages = training.getStageList();
        Intent intent = new Intent(view.getContext(), StagesActivity.class);
        intent.putExtra("stages",(Serializable) stages);
        view.getContext().startActivity(intent);
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
        notifyDataSetChanged();
    }

    public static class TrainingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView timeTextView;
        private TextView stagesSizeTextView;

        private ImageView difficultLevelImageView;
        private CardView cardView;
        private TrainingsAdapter listener;

        public TrainingViewHolder(@NonNull View itemView, TrainingsAdapter listener) {
            super(itemView);
            this.titleTextView = itemView.findViewById(R.id.trainingTitleTextView);
            this.timeTextView = itemView.findViewById(R.id.trainingTimeTextView);
            this.difficultLevelImageView = itemView.findViewById(R.id.trainingDifficultLevelImageView);
            this.stagesSizeTextView = itemView.findViewById(R.id.stageSizeTextView);
            this.cardView = itemView.findViewById(R.id.trainingCardView);
            cardView.setOnClickListener(this);
            this.listener = listener;
        }

        public void bindData(Training training, Context context) {
            titleTextView.setText(training.getTitle());
            timeTextView.setText(String.valueOf("time: " + training.getTime() + " min"));
            switch (training.getDifficult()) {
                case 1:
                    Drawable easy = ContextCompat.getDrawable(context, R.drawable.easy);
                    difficultLevelImageView.setImageDrawable(easy);
                    break;
                case 2:
                    Drawable medium = ContextCompat.getDrawable(context, R.drawable.medium);
                    difficultLevelImageView.setImageDrawable(medium);
                    break;
                case 3:
                    Drawable hard = ContextCompat.getDrawable(context, R.drawable.hard);
                    difficultLevelImageView.setImageDrawable(hard);
                    break;
                default:
                    break;

            }
            stagesSizeTextView.setText(String.valueOf("Stages: " + Optional.ofNullable(training.getStageList()).map(List::size).orElse(0)));
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onTrainingClick(position,view);
            }
        }
    }
}
