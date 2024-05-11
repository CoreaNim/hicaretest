package net.hicare.hicaretest.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.hicare.hicaretest.domain.repository.SharePrefsRepository
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PreferenceModule {
    @Provides
    @Singleton
    @Named("HICARE_PREFERENCE")
    fun providePreferenceName() = "hicare-preference"

    @Provides
    @Singleton
    fun provideSharePreference(
        @Named("HICARE_PREFERENCE") preferenceName: String,
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharePrefsRepo(sharePrefs: SharedPreferences): SharePrefsRepository =
        SharePrefsRepository(sharePrefs)
}