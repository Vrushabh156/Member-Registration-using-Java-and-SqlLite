package com.vrushabh.assignment_member_registration.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.vrushabh.assignment_member_registration.data.model.Member;
import com.vrushabh.assignment_member_registration.data.repository.MemberRepository;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MemberViewModel extends ViewModel {
    private final MemberRepository repository;
    private final MutableLiveData<List<Member>> membersLiveData = new MutableLiveData<>();

    @Inject
    public MemberViewModel(MemberRepository repository) {
        this.repository = repository;
        loadMembers();
    }

    public LiveData<List<Member>> getMembers() {
        return membersLiveData;
    }

    public void addMember(Member member) {
        repository.insertMember(member);
        loadMembers();
    }

    private void loadMembers() {
        List<Member> members = repository.getAllMembers();
        membersLiveData.setValue(members);
    }
}
