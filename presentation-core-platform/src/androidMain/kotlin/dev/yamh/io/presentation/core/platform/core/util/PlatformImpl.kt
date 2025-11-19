package dev.yamh.io.presentation.core.platform.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri

internal class PlatformImpl(val context: Context) : Platform {
    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}