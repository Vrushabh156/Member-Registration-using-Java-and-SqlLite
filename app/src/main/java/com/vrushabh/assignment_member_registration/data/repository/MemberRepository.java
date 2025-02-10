package com.vrushabh.assignment_member_registration.data.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.vrushabh.assignment_member_registration.data.local.MemberDatabaseHelper;
import com.vrushabh.assignment_member_registration.data.model.Member;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MemberRepository {
    private final MemberDatabaseHelper dbHelper;

    @Inject
    public MemberRepository(MemberDatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void insertMember(Member member) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("mobileNumber", member.getMobileNumber());
        values.put("name", member.getName());
        values.put("role", member.getRole());
        values.put("subscriptionFee", member.getSubscriptionFee());
        values.put("depositAmount", member.getDepositAmount());
        values.put("loanAmount", member.getLoanAmount());
        values.put("gender", member.getGender());
        values.put("dob", member.getDob());
        values.put("maritalStatus", member.getMaritalStatus());
        values.put("joiningDate", member.getJoiningDate());
        values.put("marriageDate", member.getMarriageDate());
        values.put("caste", member.getCaste());
        values.put("category", member.getCategory());
        values.put("aadharNumber", member.getAadharNumber());
        values.put("religion", member.getReligion());

        db.insert("members", null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("members", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Member member = new Member();
                member.setMobileNumber(cursor.getString(cursor.getColumnIndex("mobileNumber")));
                member.setName(cursor.getString(cursor.getColumnIndex("name")));
                member.setRole(cursor.getString(cursor.getColumnIndex("role")));
                member.setSubscriptionFee(cursor.getDouble(cursor.getColumnIndex("subscriptionFee")));
                member.setDepositAmount(cursor.getDouble(cursor.getColumnIndex("depositAmount")));
                member.setLoanAmount(cursor.getDouble(cursor.getColumnIndex("loanAmount")));
                member.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                member.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                member.setMaritalStatus(cursor.getString(cursor.getColumnIndex("maritalStatus")));
                member.setJoiningDate(cursor.getString(cursor.getColumnIndex("joiningDate")));
                member.setMarriageDate(cursor.getString(cursor.getColumnIndex("marriageDate")));
                member.setCaste(cursor.getString(cursor.getColumnIndex("caste")));
                member.setCategory(cursor.getString(cursor.getColumnIndex("category")));
                member.setAadharNumber(cursor.getString(cursor.getColumnIndex("aadharNumber")));
                member.setReligion(cursor.getString(cursor.getColumnIndex("religion")));
                members.add(member);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return members;
    }
}
