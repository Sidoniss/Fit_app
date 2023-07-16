package com.example.fit_app_bachelor.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_app_bachelor.databinding.FragmentDashboardBinding;
import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeAdapter;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeDAO;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeDatabase;

import java.util.List;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;

    public DashboardFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModelFactory factory = new DashboardViewModelFactory(getActivity().getApplication());
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this,factory).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText searchRecipes = binding.searchRecipes;
        final Button searchButton = binding.searchButton;
        final RecyclerView recyclerView = binding.recycerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        dashboardViewModel.getRecipes().observe(getViewLifecycleOwner(), recipes -> {
            RecipeAdapter adapter = new RecipeAdapter(recipes);
            recyclerView.setAdapter(adapter);
        });


//        RecipeDAO recipeDAO = RecipeDatabase.getInstance(requireContext()).recipeDAO();
//        List<Recipe> recipes = recipeDAO.getAllRecipes();
//        RecipeAdapter adapter = new RecipeAdapter(recipes);
//        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}