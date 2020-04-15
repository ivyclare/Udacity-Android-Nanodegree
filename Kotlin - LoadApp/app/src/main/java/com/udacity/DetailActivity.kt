package com.udacity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udacity.MainActivity
import com.udacity.utils.NotificationUtils
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {
    private var downloadId = -1
    private lateinit var downloadStatus: MainActivity.DownloadStatus
    private lateinit var fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        loadExtras()

        // Clear notification
        NotificationUtils.clearNotification(this, downloadId)

        // initializing views
        tv_file_name.text = fileName

        tv_download_status.text = if (downloadStatus == MainActivity.DownloadStatus.SUCCESS) {
            getString(R.string.success)
        } else {
            getString(R.string.fail)
        }
        layout_details.transitionToEnd()

        // back button
        btn_back.setOnClickListener {
            finish()
        }

    }

    private fun loadExtras() {
        val extras = intent.extras
        extras?.let {
            downloadId = it.getInt(EXTRA_DOWNLOAD_ID)
            downloadStatus = MainActivity.DownloadStatus.values()[it.getInt(EXTRA_DOWNLOAD_STATUS)]
            fileName = it.getString(EXTRA_FILE_NAME)!!
        }
    }

    companion object {
        private const val EXTRA_DOWNLOAD_ID = "download_id"
        private const val EXTRA_DOWNLOAD_STATUS = "download_status"
        private const val EXTRA_FILE_NAME = "file_name"

        fun withExtras(
            downloadId: Int,
            downloadStatus: MainActivity.DownloadStatus,
            fileName: String
        ): Bundle {
            return Bundle().apply {
                putInt(EXTRA_DOWNLOAD_ID, downloadId)
                putInt(EXTRA_DOWNLOAD_STATUS, downloadStatus.ordinal)
                putString(EXTRA_FILE_NAME, fileName)
            }
        }
    }
}
