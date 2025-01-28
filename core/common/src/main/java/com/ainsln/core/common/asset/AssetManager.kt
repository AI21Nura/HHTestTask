package com.ainsln.core.common.asset

import java.io.InputStream

interface AssetManager {
    fun open(assetName: String): InputStream
}
