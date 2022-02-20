package com.example.superwallet.modules

import android.content.Context
import androidx.room.Room
import com.example.superwallet.layer_data.database.LocalDataBase
import com.example.superwallet.layer_data.datasource.FireBaseDataSource
import com.example.superwallet.layer_data.datasource.LocalDataSource
import com.example.superwallet.layer_data.datasource.RemoteDataSource
import com.example.superwallet.layer_data.repository.CommonCardRepository
import com.example.superwallet.layer_data.repository.CommonMemberRepository
import com.example.superwallet.layer_domain.repository.CardRepository
import com.example.superwallet.layer_domain.repository.MemberRepository
import com.example.superwallet.layer_domain.usecase.*
import com.example.superwallet.layer_domain.usecase.card.DeleteCardUseCase
import com.example.superwallet.layer_domain.usecase.card.FindAllCardUseCase
import com.example.superwallet.layer_domain.usecase.card.InsertCardUseCase
import com.example.superwallet.layer_domain.usecase.card.UpdateCardUseCase
import com.example.superwallet.layer_domain.usecase.member.*
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {

    //USE CASE
    @Provides
    fun provideSignupUseCaseUseCase(memberRepository: MemberRepository) : SignupUseCase {
        return SignupUseCase(memberRepository)
    }
    @Provides
    fun provideLoginUseCase(memberRepository: MemberRepository) : LoginUseCase {
        return LoginUseCase(memberRepository)
    }
    @Provides
    fun provideLogoutUseCase(deleteLoginDataUseCase: DeleteLoginDataUseCase) : LogoutUseCase {
        return LogoutUseCase(deleteLoginDataUseCase)
    }
    @Provides
    fun provideFindLoginDataUseCase(memberRepository: MemberRepository) : FindLoginDataUseCase {
        return FindLoginDataUseCase(memberRepository)
    }
    @Provides
    fun provideInsertLoginDataUseCase(memberRepository: MemberRepository, deleteLoginDataUseCase: DeleteLoginDataUseCase) : InsertLoginDataUseCase {
        return InsertLoginDataUseCase(memberRepository,deleteLoginDataUseCase)
    }
    @Provides
    fun provideDeleteLoginDataUseCase(memberRepository: MemberRepository) : DeleteLoginDataUseCase {
        return DeleteLoginDataUseCase(memberRepository)
    }

    @Provides
    fun provideInsertCardUseCase(cardRepository: CardRepository) : InsertCardUseCase {
        return InsertCardUseCase(cardRepository)
    }
    @Provides
    fun provideFindAllCardUseCase(cardRepository: CardRepository) : FindAllCardUseCase {
        return FindAllCardUseCase(cardRepository)
    }
    @Provides
    fun provideUpdateCardUseCase(cardRepository: CardRepository) : UpdateCardUseCase {
        return UpdateCardUseCase(cardRepository)
    }

    @Provides
    fun provideDeleteCardUseCase(cardRepository: CardRepository) : DeleteCardUseCase {
        return DeleteCardUseCase(cardRepository)
    }
    @Provides
    fun provideValidDataUseCase() : ValidDataUseCase {
        return ValidDataUseCase()
    }

    //REPOSITORY
    @Provides
    fun provideCardRepository(localDataSource: LocalDataSource) : CardRepository {
        return CommonCardRepository(localDataSource)
    }
    @Provides
    fun provideMemberRepository(remoteDataSource: RemoteDataSource,localDataSource: LocalDataSource,fireBaseDataSource: FireBaseDataSource) : MemberRepository{
        return CommonMemberRepository(remoteDataSource,localDataSource,fireBaseDataSource)
    }

    @Provides
    fun provideRemoteDataSource(retrofit: Retrofit) : RemoteDataSource{
        return RemoteDataSource(retrofit)
    }

    @Provides
    fun provideLocalDataSource(localDataBase: LocalDataBase) : LocalDataSource{
        return LocalDataSource(localDataBase)
    }
    @Provides
    fun provideFireBaseDataSource() : FireBaseDataSource{
        return FireBaseDataSource()
    }

    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): LocalDataBase {
        return Room.databaseBuilder(
            appContext,
            LocalDataBase::class.java,
            "supper_wallet.db"
        )
        .fallbackToDestructiveMigration()
        .build()
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