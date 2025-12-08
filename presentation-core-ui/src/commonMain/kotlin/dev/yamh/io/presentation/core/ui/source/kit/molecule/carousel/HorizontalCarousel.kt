package dev.yamh.io.presentation.core.ui.source.kit.molecule.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import dev.yamh.io.presentation.core.ui.core.ext.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
public fun HorizontalCarousel(
    size: Int,
    content: @Composable (page: Int, modifier: Modifier) -> Unit,
) {
    val pagerState = rememberPagerState { size }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 60.dp),
            modifier = Modifier.fillMaxSize()
        ) { page ->

            content(
                page,
                Modifier
                    .graphicsLayer {
                        val pageOffset =
                            pagerState.calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.80f,
                            stop = 1.2f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1.2f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            )

        }
    }

}