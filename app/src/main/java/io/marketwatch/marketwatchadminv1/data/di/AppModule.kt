package io.marketwatch.marketwatchadminv1.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.marketwatch.marketwatchadminv1.data.NetworkRepositoryImpl
import io.marketwatch.marketwatchadminv1.data.network.ApiService
import io.marketwatch.marketwatchadminv1.data.network.BASE_URL
import io.marketwatch.marketwatchadminv1.domain.repository.NetworkRepository
import io.marketwatch.marketwatchadminv1.domain.use_cases.Create_tips_use_case
import io.marketwatch.marketwatchadminv1.domain.use_cases.Delete_ActiveTips_Use_Case
import io.marketwatch.marketwatchadminv1.domain.use_cases.GetActiveTips_UseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRetrofitBuilder():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiService():ApiService{
        return provideRetrofitBuilder().create(ApiService::class.java)
    }

    @Provides
    fun provideNetworkRepo():NetworkRepository{
        return NetworkRepositoryImpl(provideApiService());
    }

    @Provides
    fun provideActiveTipsUseCase():GetActiveTips_UseCase{
        return GetActiveTips_UseCase(provideNetworkRepo())
    }

    @Provides
    fun provideCreateTips():Create_tips_use_case{
        return Create_tips_use_case(provideNetworkRepo())
    }

    @Provides
    fun provideDeleteById():Delete_ActiveTips_Use_Case{
        return Delete_ActiveTips_Use_Case(provideNetworkRepo())
    }
}