package com.vrushabh.assignment_member_registration.presentation.actiivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.RadioButton;
import android.app.DatePickerDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

import com.vrushabh.assignment_member_registration.R;
import com.vrushabh.assignment_member_registration.data.model.Member;
import com.vrushabh.assignment_member_registration.databinding.ActivityAddMemberBinding;
import com.vrushabh.assignment_member_registration.presentation.viewmodel.MemberViewModel;
import com.vrushabh.assignment_member_registration.util.SnackBarUtil;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddMemberActivity extends AppCompatActivity {
    private ActivityAddMemberBinding binding;
    private MemberViewModel viewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMemberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MemberViewModel.class);

        // Set up date pickers
        setupDatePickers();

        binding.btnSubmit.setOnClickListener(v -> {
            if (validateInput()) {
                // Create Member object
                Member member = new Member();
                member.setMobileNumber(binding.etMobileNumber.getText().toString());
                member.setName(binding.etName.getText().toString());

                // Get selected role
                int selectedRoleId = binding.rgRole.getCheckedRadioButtonId();
                if (selectedRoleId != -1) {
                    RadioButton selectedRole = findViewById(selectedRoleId);
                    member.setRole(selectedRole.getText().toString());
                }

                try {
                    member.setSubscriptionFee(Double.parseDouble(binding.etSubscriptionFee.getText().toString()));
                    member.setDepositAmount(Double.parseDouble(binding.etDepositAmount.getText().toString()));
                    member.setLoanAmount(Double.parseDouble(binding.etLoanAmount.getText().toString()));
                } catch (NumberFormatException e) {
                    SnackBarUtil.showSnackBar(binding.getRoot(), "Please enter valid numbers for fees and amounts");
                    return;
                }

                // Get selected gender
                int selectedGenderId = binding.rgGender.getCheckedRadioButtonId();
                if (selectedGenderId != -1) {
                    RadioButton selectedGender = findViewById(selectedGenderId);
                    member.setGender(selectedGender.getText().toString());
                }

                member.setDob(binding.etDob.getText().toString());
                member.setJoiningDate(binding.etDoJ.getText().toString());

                // Get selected marital status
                int selectedMaritalStatusId = binding.rgMaritalStatus.getCheckedRadioButtonId();
                if (selectedMaritalStatusId != -1) {
                    RadioButton selectedMaritalStatus = findViewById(selectedMaritalStatusId);
                    member.setMaritalStatus(selectedMaritalStatus.getText().toString());
                }

                member.setMarriageDate(binding.etDoM.getText().toString());
                member.setCaste(binding.tvCaste.getText().toString());
                member.setCategory(binding.tvCategory.getText().toString());
                member.setAadharNumber(binding.tvAdhar.getText().toString());
                member.setReligion(binding.tvReligion.getText().toString());

                // Add member to database
                viewModel.addMember(member);
                SnackBarUtil.showSnackBar(binding.getRoot(), "Member added successfully!");

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    Intent intent = new Intent(AddMemberActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }, 1000);
            }
        });
    }

    private void setupDatePickers() {
        binding.etDob.setOnClickListener(v -> showDatePickerDialog((date) -> binding.etDob.setText(date)));
        binding.etDoJ.setOnClickListener(v -> showDatePickerDialog((date) -> binding.etDoJ.setText(date)));
        binding.etDoM.setOnClickListener(v -> showDatePickerDialog((date) -> binding.etDoM.setText(date)));
    }

    private void showDatePickerDialog(OnDateSetListener listener) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            listener.onDateSet(date);
        }, year, month, day);

        // Set the maximum date to the current date
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }

    private boolean validateInput() {
        if (binding.etMobileNumber.getText().toString().isEmpty() ||
                binding.etName.getText().toString().isEmpty() ||
                binding.rgRole.getCheckedRadioButtonId() == -1 ||
                binding.etSubscriptionFee.getText().toString().isEmpty() ||
                binding.etDepositAmount.getText().toString().isEmpty() ||
                binding.etLoanAmount.getText().toString().isEmpty() ||
                binding.rgGender.getCheckedRadioButtonId() == -1 ||
                binding.etDob.getText().toString().isEmpty() ||
                binding.rgMaritalStatus.getCheckedRadioButtonId() == -1 ||
                binding.etDoJ.getText().toString().isEmpty() ||
                binding.tvCaste.getText().toString().isEmpty() ||
                binding.tvCategory.getText().toString().isEmpty() ||
                binding.tvAdhar.getText().toString().isEmpty() ||
                binding.tvReligion.getText().toString().isEmpty()) {

            SnackBarUtil.showSnackBar(binding.getRoot(), "Please fill in all required fields");
            return false;
        }

        if (binding.etMobileNumber.getText().toString().length() != 10) {
            SnackBarUtil.showSnackBar(binding.getRoot(), "Please enter a valid 10-digit mobile number");
            return false;
        }

        try {
            double subscriptionFee = Double.parseDouble(binding.etSubscriptionFee.getText().toString());
            if (subscriptionFee <= 0) {
                SnackBarUtil.showSnackBar(binding.getRoot(), "Subscription fee must be a positive number");
                return false;
            }
        } catch (NumberFormatException e) {
            SnackBarUtil.showSnackBar(binding.getRoot(), "Please enter a valid subscription fee");
            return false;
        }

        if (binding.tvAdhar.getText().toString().length() != 12) {
            SnackBarUtil.showSnackBar(binding.getRoot(), "Please enter a valid 12-digit Aadhaar number");
            return false;
        }

        return true;
    }

    private interface OnDateSetListener {
        void onDateSet(String date);
    }
}
