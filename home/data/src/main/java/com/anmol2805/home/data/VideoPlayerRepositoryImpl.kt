package com.anmol2805.home.data

import android.app.Application
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.anmol2805.base.domain.Result
import com.anmol2805.home.domain.VideoPlayerRepository
import com.anmol2805.home.domain.VideosResponse
import com.anmol2805.home.domain.model.BookmarkRequestDraft
import com.anmol2805.home.domain.model.VideoDetails
import javax.inject.Inject

class VideoPlayerRepositoryImpl @Inject constructor(
    application: Application
) : VideoPlayerRepository {

    private val context = application

    override suspend fun fetchVideos(): Result<VideosResponse> {
        val prefs = context.getSharedPreferences("trell.session", Context.MODE_PRIVATE)
        val uriSet = prefs.getStringSet("uriSet", emptySet())

        val videos = mutableListOf<VideoDetails>()
        val cursor: Cursor?

        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Video.Media._ID
        )

        val orderBy = MediaStore.Images.Media.DATE_TAKEN
        cursor = context.contentResolver.query(uri, projection, null, null, "$orderBy DESC")

        cursor?.let {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val contentUri: Uri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                var isBookMarked = false
                if (uriSet?.contains("${contentUri}/true") == true) {
                    isBookMarked = true
                }
                videos.add(
                    VideoDetails(
                        videoUri = contentUri.toString(),
                        isBookMarked = isBookMarked
                    )
                )
            }

        }

        return Result.Success(VideosResponse(videos))
    }

    override suspend fun setBookMark(bookmarkRequestDraft: BookmarkRequestDraft) {
        val prefs = context.getSharedPreferences("trell.session", Context.MODE_PRIVATE)
        val uriSet = prefs.getStringSet("uriSet", emptySet())
        val set = mutableSetOf<String>()
        uriSet?.let {
            set.addAll(it)
        }
        set.apply {
            if (contains("${bookmarkRequestDraft.uri}/true"))
                remove("${bookmarkRequestDraft.uri}/true")
            add("${bookmarkRequestDraft.uri}/${bookmarkRequestDraft.isBookMarked}")
        }
        with(prefs.edit()) {
            putStringSet("uriSet", set.toSet())
            apply()
        }
    }

}