package si.damjanh.androidhilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import si.damjanh.androidhilt.data.net.IPlantsNetworkService
import si.damjanh.androidhilt.data.net.PlantsNetworkService

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providPlantsNetworkService(retrofit: Retrofit): IPlantsNetworkService =
        PlantsNetworkService(retrofit)

    @Provides
    fun provideRetorfit(okHttpClient: OkHttpClient, converter: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()
}