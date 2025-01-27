package com.ainsln.core.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface IntentManager {
    fun openWebPage(url: String): Boolean
}

internal class BaseIntentManager @Inject constructor(
    @ApplicationContext val context: Context
) : IntentManager {

    override fun openWebPage(url: String): Boolean {
        var newUrl = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            newUrl = "http://$url"

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(newUrl)
        )
        return startActivityIfCanResolve(intent)
    }

    private fun startActivityIfCanResolve(intent: Intent): Boolean {
        return with(context){
            if (intent.resolveActivity(packageManager) != null){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                true
            } else
                false
        }
    }
}
