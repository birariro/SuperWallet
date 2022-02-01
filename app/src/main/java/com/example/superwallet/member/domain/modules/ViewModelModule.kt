package com.example.superwallet.member.domain.modules

import com.example.superwallet.member.data.repository.CommonMemberRepository
import com.example.superwallet.member.domain.repository.MemberRepository
import com.example.superwallet.member.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {
    @Provides
    fun provideLoginUseCase(memberRepository: MemberRepository) : LoginUseCase{
        return LoginUseCase(memberRepository)
    }
    @Provides
    fun provideMemberRepository() : MemberRepository{
        return CommonMemberRepository()
    }
}