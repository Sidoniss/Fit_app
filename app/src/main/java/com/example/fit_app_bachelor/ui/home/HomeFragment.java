package com.example.fit_app_bachelor.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.databinding.FragmentHomeBinding;
import com.example.fit_app_bachelor.ui.home.service.TrainingDatabase;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CategoryViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrainingDatabase db = TrainingDatabase.getInstance(requireContext());
        viewModel = new ViewModelProvider(this,new CategoryViewModel.Factory(db.trainingDAO()))
                .get(CategoryViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.treningTextView;
        final ImageButton armImageButton = binding.armImageButton;
        final ImageButton legsImageButton = binding.legsImageButton;
        final ImageButton bellyImageButton = binding.bellyImageButton;
        final ImageButton cardioImageButton = binding.cardioImageButton;
        final ImageButton videoImageButton = binding.videoImageButton;



        armImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseTrainingActivity.class);
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });

        legsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseTrainingActivity.class);
                intent.putExtra("category",2);
                startActivity(intent);
            }
        });

        bellyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseTrainingActivity.class);
                intent.putExtra("category",3);
                startActivity(intent);
            }
        });

        cardioImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseTrainingActivity.class);
                intent.putExtra("category",4);
                startActivity(intent);
            }
        });

        videoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChooseTrainingActivity.class);
                intent.putExtra("category",5);
                startActivity(intent);
            }
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}