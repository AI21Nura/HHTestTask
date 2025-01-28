package com.ainsln.core.common.asset

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class BaseAssetManager @Inject constructor(
    @ApplicationContext private val context: Context
) : AssetManager {

    override fun open(assetName: String): InputStream {
        return context.assets.open(assetName)
    }
}
