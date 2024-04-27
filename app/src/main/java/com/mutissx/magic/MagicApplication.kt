package com.mutissx.magic

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import com.mutissx.magic.di.koinModules
import com.mutissx.magic.core.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MagicApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            printLogger(Level.ERROR)
            androidContext(this@MagicApplication)
            modules(koinModules)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve(Constants.IMAGE_CACHE_NAME))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}
