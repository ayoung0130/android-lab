package com.android.my_application.util

import com.android.my_application.data.repo.BookmarkRepository
import com.android.my_application.data.repo.BookmarkRepositoryImpl
import com.android.my_application.data.repo.KakaoRepository
import com.android.my_application.data.repo.KakaoRepositoryImpl
import com.android.my_application.data.source.local.BookmarkLocalDataSource
import com.android.my_application.data.source.local.BookmarkLocalDataSourceImpl
import com.android.my_application.data.source.remote.KakaoRemoteDataSource
import com.android.my_application.data.source.remote.KakaoRemoteDataSourceImpl
import com.android.my_application.room.BookmarkDao

object InjectUtil {

    // Kakao
    fun provideKakaoService() = RetrofitUtil.getKAKAOService()

    fun provideKakaoRemoteDataSource(): KakaoRemoteDataSource =
        KakaoRemoteDataSourceImpl.getInstance(provideKakaoService())

    fun provideKakaoRepository(): KakaoRepository =
        KakaoRepositoryImpl.getInstance(provideKakaoRemoteDataSource())


    // Bookmark
    fun provideBookmarkDao(): BookmarkDao {
        return RoomUtil.getBookmarkDao()
    }

    fun provideBookmarkLocalDataSource(): BookmarkLocalDataSource{
        return BookmarkLocalDataSourceImpl.getInstance(provideBookmarkDao())
    }

    fun provideBookmarkRepository(): BookmarkRepository {
        return BookmarkRepositoryImpl.getInstance(provideBookmarkLocalDataSource())
    }
}