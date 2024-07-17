package so.notion.news

import android.app.Application
import android.graphics.Bitmap
import android.util.LruCache
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import so.notion.news.di.appModules

class NewsApp : Application() {

    lateinit var memoryCache: LruCache<String, Bitmap>

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        // Use 1/8th of the available memory for this memory cache.
        val cacheSize = maxMemory / 8

        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NewsApp)
            modules(appModules)
        }
    }

    companion object {
        private lateinit var INSTANCE: NewsApp
        fun getInstance() = INSTANCE
    }
}