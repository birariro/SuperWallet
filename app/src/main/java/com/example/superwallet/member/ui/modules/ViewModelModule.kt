package com.example.superwallet.member.ui.modules

import com.example.superwallet.member.data.datasource.RemoteDataSource
import com.example.superwallet.member.data.repository.CommonMemberRepository
import com.example.superwallet.member.domain.repository.MemberRepository
import com.example.superwallet.member.domain.usecase.LoginUseCase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {
    @Provides
    fun provideLoginUseCase(memberRepository: MemberRepository) : LoginUseCase{
        return LoginUseCase(memberRepository)
    }
    @Provides
    fun provideMemberRepository(remoteDataSource: RemoteDataSource) : MemberRepository{
        return CommonMemberRepository(remoteDataSource)
    }

    @Provides
    fun provideRemoteDataSource(retrofit: Retrofit) : RemoteDataSource{
        return RemoteDataSource(retrofit)
    }


    @Provides
    fun provideRetrofit(): Retrofit{
        val RASE_URL = "https://run.mocky.io/"

        return Retrofit.Builder()
            .baseUrl(RASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}