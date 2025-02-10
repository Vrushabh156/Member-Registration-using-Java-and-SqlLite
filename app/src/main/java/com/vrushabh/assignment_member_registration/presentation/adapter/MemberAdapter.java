package com.vrushabh.assignment_member_registration.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.vrushabh.assignment_member_registration.data.model.Member;
import com.vrushabh.assignment_member_registration.databinding.ItemMemberBinding;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private final List<Member> members;

    public MemberAdapter(List<Member> members) {
        this.members = members;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMemberBinding binding = ItemMemberBinding.inflate(inflater, parent, false);
        return new MemberViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = members.get(position);

        holder.binding.tvName.setText("Member Name: " + member.getName());
        holder.binding.tvMobile.setText("Mobile No: " + member.getMobileNumber());
        holder.binding.tvRole.setText("Member Role: " + member.getRole());
        holder.binding.tvAmount.setText("Subscription Amt.: " + member.getSubscriptionFee());
        holder.binding.tvLoanAmount.setText(String.valueOf(member.getLoanAmount()));
        holder.binding.tvDepositAmount.setText(String.valueOf(member.getDepositAmount()));
    }


    @Override
    public int getItemCount() {
        return members.size();
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        final ItemMemberBinding binding;

        public MemberViewHolder(@NonNull ItemMemberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
