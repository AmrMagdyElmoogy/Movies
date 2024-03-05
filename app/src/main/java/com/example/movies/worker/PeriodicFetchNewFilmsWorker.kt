package com.example.movies.worker

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.movies.MainActivity
import com.example.movies.R
import com.example.movies.trends.domain.MoviesTrendsRepository
import com.example.movies.utils.CHANNEL_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PeriodicFetchNewFilmsWorker
    @AssistedInject
    constructor(
        private val moviesTrendsRepo: MoviesTrendsRepository,
        @Assisted private val context: Context,
        @Assisted params: WorkerParameters,
    ) : CoroutineWorker(
            context,
            params,
        ) {
        override suspend fun doWork(): Result {
            moviesTrendsRepo.getNewReleasesFilmsRemotely()
            moviesTrendsRepo.getTopRatedFilmsRemotely()
            sendNotification()
            return Result.success()
        }

        private fun sendNotification() {
            val pendingIntent =
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE,
                )

            val notificationBody =
                NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                    .setContentTitle("Movies")
                    .setContentText("Hurry up for new released films!")
                    .setSmallIcon(R.drawable.launcher_icon)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build()

            val notificationManager =
                applicationContext.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notificationBody)
        }
    }
