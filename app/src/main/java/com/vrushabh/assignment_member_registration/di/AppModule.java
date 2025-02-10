package com.vrushabh.assignment_member_registration.di;

import android.content.Context;
import com.vrushabh.assignment_member_registration.data.local.MemberDatabaseHelper;
import com.vrushabh.assignment_member_registration.data.repository.MemberRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public MemberDatabaseHelper provideDatabaseHelper(@ApplicationContext Context context) {
        return new MemberDatabaseHelper(context);
    }

    @Provides
    @Singleton
    public MemberRepository provideMemberRepository(MemberDatabaseHelper dbHelper) {
        return new MemberRepository(dbHelper);
    }
}

