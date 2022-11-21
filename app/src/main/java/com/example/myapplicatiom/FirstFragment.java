package com.example.myapplicatiom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplicatiom.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TextView showCountTextView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showCountTextView = view.findViewById(R.id.textview_first);


        binding.randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                Bundle bundle = new Bundle();
                String countR = showCountTextView.getText().toString();

                bundle.putString("count", countR);

                getParentFragmentManager().setFragmentResult("dataF1", bundle);
                showCountTextView.setText("");

                //SecondFragment secondFragment = new SecondFragment();
                //secondFragment.setArguments(bundle);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,secondFragment).commit();
            }
        });
        view.findViewById(R.id.toast_button).setOnClickListener(view1 -> {
            Toast myToast = Toast.makeText(getActivity(), "Hello Toast!", Toast.LENGTH_SHORT);
            myToast.show();
        });

        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                countMe(view);
            }
        });

        // Botón para abrir Activity2
        view.findViewById(R.id.button_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity2();
            }
        });
        // FIN Botón abrir Activity2
    }

    void openActivity2(){
        Intent intent = new Intent(getContext(), MainActivity2.class);
        startActivity(intent);
        //finish();
    }
    @SuppressLint("SetTextI18n")
    private void countMe(View view) {
        // Get the value of the text view
        showCountTextView = view.findViewById(R.id.textview_first);
        String countString = showCountTextView.getText().toString();
        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}