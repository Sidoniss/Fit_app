package com.example.fit_app_bachelor.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;

    final String[] items = {"no lactose","no gluten","keto","without meat"};
    final boolean[] checkedItems = {false,false,false,false};

    private List<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter adapter;

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

        dashboardViewModel.getRecipes().observe(getViewLifecycleOwner(), recipeList -> {
            this.recipes = recipeList;
            adapter = new RecipeAdapter(recipes);
            recyclerView.setAdapter(adapter);
        });

        searchRecipes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    filter(s.toString(), adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("filter")
                        .setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                for (int i=0; i<checkedItems.length; i++) {
                                    if (checkedItems[i]) {
                                        Log.d("DashboardFragment",items[i] + "was selected.");
                                    }
                                }
                            }
                        });
                builder.create().show();
            }
        });

        return root;
    }


    private void filter(String text, RecipeAdapter adapter) {
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (recipe.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredRecipes.add(recipe);
            }
        }

        adapter.filterList(filteredRecipes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}