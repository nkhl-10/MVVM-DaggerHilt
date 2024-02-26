package mvvm.app.dragerhill.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mvvm.app.dragerhill.network.ApiInterface
import mvvm.app.dragerhill.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//NKHL_7 that is module in declare in all the

//NKHL_8
@Module
@InstallIn(SingletonComponent::class)

object AppModules {

    //NKHL_9
    @Provides
    fun providesURL() = Constant.BaseUrl

    //NKHL_9
    @Provides
    @Singleton
    fun apiService(url: String): ApiInterface =
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
}