package com.ivy.bakingapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivy.bakingapp.Listeners.RecyclerClickListener;
import com.ivy.bakingapp.R;
import com.ivy.bakingapp.RecipeDetail;
import com.ivy.bakingapp.StepDetailActivity;
import com.ivy.bakingapp.adapters.StepAdapter;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.RecyclerViewMarginDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment {

    private StepAdapter StepAdapter;
    private int currentStepIndex;

    @BindView(R.id.steps_recycler)
    RecyclerView StepRecyclerView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment StepsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StepsFragment newInstance(ArrayList<StepModel> steps) {
        StepsFragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("steps", steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView();

        StepRecyclerView.addOnItemTouchListener(new RecyclerClickListener(getActivity(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                    Intent intents = new Intent(getActivity(), StepDetailActivity.class);
                    ArrayList<StepModel> stepModel = getArguments().getParcelableArrayList("steps");
                    StepModel steps = stepModel.get(position);
                    currentStepIndex = stepModel.indexOf(steps);
                    intents.putExtra("stepModel",stepModel);
                    intents.putExtra("steps", steps);
                    intents.putExtra("currentStepIndex",currentStepIndex);
                    startActivity(intents);
            }
        }));

        return view;
    }


    private void setupRecyclerView() {
        int column = 1;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        StepRecyclerView.setLayoutManager(layoutManager);
        StepRecyclerView.addItemDecoration(decoration);
        ArrayList<StepModel> steps = getArguments().getParcelableArrayList("steps");
        StepAdapter = new StepAdapter(getContext(),steps);
        StepRecyclerView.setAdapter(StepAdapter);
    }


}
