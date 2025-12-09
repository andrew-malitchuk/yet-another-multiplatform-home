package dev.yamh.io.presentation.core.ui.source.kit.atom.stack

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import androidx.compose.ui.input.pointer.consumePositionChange
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.MouseScrollDown32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.MouseScrollUp32
import dev.yamh.presentation.core.styling.core.Theme


@Composable
public fun StackItemContainer(modifier: Modifier = Modifier, card: @Composable () -> Unit) {
    var rotation by remember { mutableStateOf(0f) }

    Layout(content = card, modifier = modifier.graphicsLayer {
        cameraDistance = 12f * density
    }.pointerInput(Unit) {
        detectDragGesturesAfterLongPress { change, dragAmount ->
            rotation += dragAmount.x * 0.1f
            change.consume()
        }
    }) { measurables, constraints ->
        val placeable = measurables.first().measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }
}


//
@DslMarker
public annotation class StackScopeMarker

@StackScopeMarker
public interface StackScope {
    public fun item(
        key: Any? = null,
        content: @Composable (selected: Boolean) -> Unit
    )

    public fun <T> items(
        data: List<T>,
        key: ((index: Int, item: T) -> Any)? = null,
        itemContent: @Composable (index: Int, item: T, selected: Boolean) -> Unit
    )
}

private class StackScopeImpl : StackScope {
    data class Entry(val key: Any?, val content: @Composable (Boolean) -> Unit)

    val entries = mutableListOf<Entry>()

    override fun item(
        key: Any?,
        content: @Composable (selected: Boolean) -> Unit
    ) {
        entries += Entry(key, content)
    }

    override fun <T> items(
        data: List<T>,
        key: ((index: Int, item: T) -> Any)?,
        itemContent: @Composable (index: Int, item: T, selected: Boolean) -> Unit
    ) {
        data.forEachIndexed { index, item ->
            val k = key?.invoke(index, item)
            item(k) { selected -> itemContent(index, item, selected) }
        }
    }
}


@Composable
public fun StackVertical(
    modifier: Modifier = Modifier,
    stackedState: Boolean = false,
    paddingBetweenItems: Dp = 42.dp,
    paddingHorizontalDefault: Dp = 32.dp,
    paddingHorizontalSelected: Dp = 16.dp,
    visibleItems: Int = 3,
    content: StackScope.() -> Unit,
) {
    val scope = StackScopeImpl().apply { content() }

    val cardRotation by animateFloatAsState(targetValue = if (stackedState) 10f else 0f, label = "")
    val cardOffsetY by animateDpAsState(targetValue = if (stackedState) 20.dp else 0.dp, label = "")
    val cardScale by animateFloatAsState(targetValue = if (stackedState) 0.95f else 1f, label = "")

    val density = LocalDensity.current

    // start index of the visible window [firstVisible .. firstVisible+visibleItems-1]
    var firstVisible by remember { mutableIntStateOf(0) }
    val lastPossibleFirst = (scope.entries.size - visibleItems).coerceAtLeast(0)

    // selection is local to the visible window: 0..visibleItems-1 (or -1 for none)
    var selectedCard by remember { mutableIntStateOf(-1) }

    var direction by remember { mutableStateOf(MouseScrollDown32) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Bottom
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            val width = maxWidth
            val height = width / 1.5f
            val window = scope.entries.drop(firstVisible).take(visibleItems)


            window.forEachIndexed { localIndex, entry ->
                val centerOffset = this@BoxWithConstraints.maxHeight / 2f - height / 2f
                val offsetY = animateDpAsState(
                    targetValue = if (localIndex == selectedCard) -centerOffset
                    else -16.dp + (localIndex * paddingBetweenItems.value).dp,
                    animationSpec = tween(durationMillis = 500),
                    label = "offsetY"
                )

                val horizontalPadding by animateDpAsState(
                    targetValue = if (selectedCard == localIndex) paddingHorizontalSelected else paddingHorizontalDefault,
                    label = "pad"
                )

                val interactionSource = remember { MutableInteractionSource() }
                val noRippleClickable = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    selectedCard = if (selectedCard == localIndex) -1 else localIndex
                }

                StackItemContainer(
                    modifier = Modifier
                        .offset(y = offsetY.value)
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .graphicsLayer {
                            translationY = with(density) { cardOffsetY.toPx() * localIndex }
                            scaleX = cardScale
                            scaleY = cardScale
                        }
                        .offset(y = offsetY.value)
                        .then(noRippleClickable)
                        .padding(horizontal = horizontalPadding)
                        .onVerticalSwipe(
                            onSwipeUp = {
                                if (selectedCard != -1) return@onVerticalSwipe
                                // show next window: shift toward larger indices if possible
                                if (firstVisible < lastPossibleFirst) {
                                    firstVisible += 1
                                    selectedCard = -1
                                } else {
                                    direction = MouseScrollUp32
                                }
                            },
                            onSwipeDown = {
                                if (selectedCard != -1) return@onVerticalSwipe
                                // show previous window: shift toward smaller indices if possible
                                if (firstVisible > 0) {
                                    firstVisible -= 1
                                    // optional: reset local selection
                                    selectedCard = -1
                                } else {
                                    direction = MouseScrollDown32
                                }

                            }
                        ),
                ) {
                    entry.content(selectedCard == localIndex)
                }
            }
            if (scope.entries.size > visibleItems && (selectedCard == -1)) {
                Icon(
                    imageVector = direction,
                    contentDescription = null,
                    tint = Theme.color.primary1,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(
                            y = (-(16 + paddingBetweenItems.value) * 5 - 32).dp
                        )
                        .size(32.dp)
                )
            }
        }
    }
}

//

public fun Modifier.onVerticalSwipe(
    minDistance: Dp = 64.dp,
    minVelocity: Float = 1200f, // px/s
    consume: Boolean = true,
    onSwipeUp: () -> Unit,
    onSwipeDown: () -> Unit
): Modifier = this.then(
    Modifier.pointerInput(minDistance, minVelocity, consume) {
        val minDistancePx = minDistance.toPx()

        awaitEachGesture {
            // 1) wait for first pointer
            val down = awaitFirstDown(requireUnconsumed = false)
            val id = down.id

            var totalDy = 0f
            val vt = VelocityTracker().apply {
                addPosition(down.uptimeMillis, down.position)
            }

            var current: PointerInputChange = down

            // 2) track until release/cancel
            while (true) {
                val event = awaitPointerEvent(PointerEventPass.Main)
                // find our pointer
                val change = event.changes.firstOrNull { it.id == id } ?: continue

                // delta without positionChange()
                val dy = change.position.y - change.previousPosition.y
                totalDy += dy
                vt.addPosition(change.uptimeMillis, change.position)

                if (consume && change.pressed) {
                    change.consumePositionChange()
                }

                // released/cancelled?
                if (!change.pressed) {
                    val vy = vt.calculateVelocity().y // px/s; up is negative
                    val enoughDistance = abs(totalDy) >= minDistancePx
                    val enoughVelocity = abs(vy) >= minVelocity
                    if (enoughDistance || enoughVelocity) {
                        if (totalDy < 0f) onSwipeUp() else onSwipeDown()
                    }
                    break
                }

                current = change
            }
        }
    }
)


//

public enum class SlideDir { Up, Down }

@Composable
public fun StackVerticalAnimated(
    modifier: Modifier = Modifier,
    stackedState: Boolean = false,
    paddingBetweenItems: Dp = 42.dp,
    paddingHorizontalDefault: Dp = 32.dp,
    paddingHorizontalSelected: Dp = 16.dp,
    visibleItems: Int = 3,
    content: StackScope.() -> Unit,
) {
    val scope = remember { StackScopeImpl() }.apply(content)

    var firstVisible by remember { mutableIntStateOf(0) }
    val lastPossibleFirst = (scope.entries.size - visibleItems).coerceAtLeast(0)
    var selectedCard by remember { mutableIntStateOf(-1) }
    var direction by remember { mutableStateOf(MouseScrollDown32) }
    var slideDir by remember { mutableStateOf<SlideDir?>(null) }


    val cardRotation by animateFloatAsState(if (stackedState) 10f else 0f, label = "")
    val cardOffsetY by animateDpAsState(if (stackedState) 20.dp else 0.dp, label = "")
    val cardScale by animateFloatAsState(if (stackedState) 0.95f else 1f, label = "")
    val density = LocalDensity.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            // фактичні розміри картки і стеку
            val cardHeight = maxWidth / 1.5f
            val stackHeight = cardHeight + paddingBetweenItems * (visibleItems - 1)
            val stackHeightPx = with(density) { stackHeight.roundToPx() }

            // Контейнер рівно під стек і з кліпом, щоб нічого «не витикалось»
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(stackHeight)
//                    .clipToBounds()          // критично
//            ) {
            val fade = 150
            val scale = 250
            val slide = 500
            val delay = 50

            AnimatedContent(
                targetState = firstVisible,
                label = "stack-window",
                transitionSpec = {
                    val fadeInAnim = fadeIn(tween(fade))
                    val fadeOutAnim = fadeOut(tween(fade))
                    val scaleInAnim = scaleIn(tween(scale, delay), initialScale = 0.7f)
                    val scaleOutAnim = scaleOut(tween(scale, delay), targetScale = 0.9f)

                    val up = (fadeInAnim + scaleInAnim +
                            slideInVertically(tween(slide, delay)) { +stackHeightPx }) togetherWith
                            (fadeOutAnim + scaleOutAnim +
                                    slideOutVertically(tween(slide, delay)) { -stackHeightPx })

                    val down = (fadeInAnim + scaleInAnim +
                            slideInVertically(tween(slide, delay)) { -stackHeightPx }) togetherWith
                            /*вихід повністю вниз на реальну висоту стеку*/
                            slideOutVertically(tween(slide, delay)) { +stackHeightPx }

                    when (slideDir) {
                        SlideDir.Up -> up
                        SlideDir.Down -> down
                        else -> fadeIn() togetherWith fadeOut()
                    }.using(SizeTransform(clip = true))
                },
                contentKey = { it } // різні інстанси для різних firstVisible
            ) { first ->
                // ВАЖЛИВО: window рахуємо тут, а не зовні
                val window = scope.entries.drop(first).take(visibleItems)

                window.forEachIndexed { localIndex, entry ->
                    val centerOffset = this@BoxWithConstraints.maxHeight - cardHeight*1.7f
                    val offsetY by animateDpAsState(
                        targetValue = if (localIndex == selectedCard) -centerOffset
                        else -16.dp + (localIndex * paddingBetweenItems.value).dp,
                        animationSpec = tween(500),
                        label = "offsetY"
                    )
                    val horizontalPadding by animateDpAsState(
                        targetValue = if (selectedCard == localIndex)
                            paddingHorizontalSelected else paddingHorizontalDefault,
                        label = "pad"
                    )
                    val interactionSource = remember { MutableInteractionSource() }

                    StackItemContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                            .graphicsLayer {
                                translationY = with(density) { cardOffsetY.toPx() * localIndex }
                                scaleX = cardScale
                                scaleY = cardScale
                            }
                            .offset(y = offsetY * 1.5f)
                            .padding(horizontal = horizontalPadding)
//                                .zIndex((visibleItems - localIndex).toFloat()) // стабільне перекриття
                            .clickable(interactionSource, indication = null) {
                                selectedCard =
                                    if (selectedCard == localIndex) -1 else localIndex
                            }
                            .onVerticalSwipe(
                                onSwipeUp = {
                                    if (selectedCard != -1) return@onVerticalSwipe
                                    if (firstVisible < lastPossibleFirst) {
                                        slideDir = SlideDir.Up
                                        firstVisible = (firstVisible + visibleItems)
//                                            .coerceAtMost(lastPossibleFirst)
                                        selectedCard = -1
                                    } else direction = MouseScrollUp32
                                },
                                onSwipeDown = {
                                    if (selectedCard != -1) return@onVerticalSwipe
                                    if (firstVisible > 0) {
                                        slideDir = SlideDir.Down
                                        firstVisible = (firstVisible - visibleItems)
//                                            .coerceAtLeast(0)
                                        selectedCard = -1
                                    } else direction = MouseScrollDown32
                                }
                            ),
                    ) {
                        entry.content(selectedCard == localIndex)
                    }
                }
            }

            if (scope.entries.size > visibleItems && (selectedCard == -1)) {
                Icon(
                    imageVector = direction,
                    contentDescription = null,
                    tint = Theme.color.primary1,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(
                            y = (-(16 + paddingBetweenItems.value) * 5 - 32).dp
                        )
                        .size(32.dp)
                )
            }
        }
    }
}