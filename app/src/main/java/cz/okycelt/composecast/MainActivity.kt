package cz.okycelt.composecast

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import cz.okycelt.composecast.ui.theme.ComposeCastTheme

class MainActivity : AppCompatActivity() {
    private val useCompose = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (useCompose) {
            setContent {
                ComposeCastTheme {
                    Surface(color = Color.Green) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AndroidView(
                                factory = { context ->
                                    MediaRouteButton(context).apply {
                                        CastButtonFactory.setUpMediaRouteButton(context, this)
                                    }
                                },
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        } else {
            setContentView(
                FrameLayout(this).apply {
                    setBackgroundResource(android.R.color.holo_purple)
                    addView(
                        MediaRouteButton(this@MainActivity).apply {
                            CastButtonFactory.setUpMediaRouteButton(this@MainActivity, this)
                        }
                    )
                }
            )
        }
    }
}