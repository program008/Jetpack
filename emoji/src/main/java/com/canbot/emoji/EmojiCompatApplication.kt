package com.canbot.emoji

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.text.emoji.bundled.BundledEmojiCompatConfig
import android.support.v4.provider.FontRequest
import android.util.Log

/**
 * Created by tao.liu on 2018/9/12.
 * description this is description
 */
class EmojiCompatApplication : Application() {
        companion object {
                private val TAG = "EmojiCompatApplication"
                /** Change this to `false` when you want to use the downloadable Emoji font.  */
                private val USE_BUNDLED_EMOJI = true
        }

        override fun onCreate() {
                super.onCreate()
                val config: EmojiCompat.Config
                if (USE_BUNDLED_EMOJI) {
                        // Use the bundled font for EmojiCompat
                        config = BundledEmojiCompatConfig(applicationContext)
                } else {
                        // Use a downloadable font for EmojiCompat
                        val fontRequest = FontRequest(
                                "com.google.android.gms.fonts",
                                "com.google.android.gms",
                                "Noto Color Emoji Compat",
                                R.array.com_google_android_gms_fonts_certs)
                        config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                                .setReplaceAll(true)
                                .registerInitCallback(object : EmojiCompat.InitCallback() {
                                        override fun onInitialized() {
                                                Log.i(TAG, "EmojiCompat initialized")
                                        }

                                        override fun onFailed(throwable: Throwable?) {
                                                Log.e(TAG, "EmojiCompat initialization failed", throwable)
                                        }
                                })
                }
                EmojiCompat.init(config)
        }
}