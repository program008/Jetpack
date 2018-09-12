package com.canbot.sharing

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
        private  var shareText: String = "https://upload.wikimedia.org/wikipedia/commons/a/aa/Sunflowers_in_field_flower.jpg"
        //private var mShareActionProvider: ShareActionProvider? = null
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_share,menu)

                // Locate MenuItem with ShareActionProvider
                /*menu.findItem(R.id.menu_item_share).also { menuItem ->
                        // Fetch and store ShareActionProvider
                        mShareActionProvider = menuItem.actionProvider as? ShareActionProvider
                }*/

                return true
        }

        @Suppress("DEPRECATION")
        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                return when (item?.itemId) {
                        R.id.action_share -> {
                                val shareIntent = ShareCompat.IntentBuilder.from(this)
                                        .setText(shareText)
                                        .setType("text/plain")
                                        .createChooserIntent()
                                        .apply {
                                                // https://android-developers.googleblog.com/2012/02/share-with-intents.html
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                        // If we're on Lollipop, we can open the intent as a document
                                                        addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                                                } else {
                                                        // Else, we will use the old CLEAR_WHEN_TASK_RESET flag
                                                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                                                }
                                        }
                                startActivity(shareIntent)
                                return true
                        }
                        else -> super.onOptionsItemSelected(item)
                }
        }

        // Call to update the share intent
       /* private fun setShareIntent(shareIntent: Intent) {
                mShareActionProvider?.setShareIntent(shareIntent)
        }*/
}
