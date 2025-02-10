package com.vrushabh.assignment_member_registration.presentation.actiivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vrushabh.assignment_member_registration.R;
import com.vrushabh.assignment_member_registration.databinding.ActivityMainBinding;
import com.vrushabh.assignment_member_registration.presentation.adapter.MemberAdapter;
import com.vrushabh.assignment_member_registration.presentation.viewmodel.MemberViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private MemberViewModel viewModel;
    private MemberAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        viewModel = new ViewModelProvider(this).get(MemberViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MemberAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        viewModel.getMembers().observe(this, members -> {

            binding.totalCount.setText("Members Count: " + members.size());
            adapter = new MemberAdapter(members);
            binding.recyclerView.setAdapter(adapter);
        });

        binding.btnAddNew.setOnClickListener(v -> {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}
