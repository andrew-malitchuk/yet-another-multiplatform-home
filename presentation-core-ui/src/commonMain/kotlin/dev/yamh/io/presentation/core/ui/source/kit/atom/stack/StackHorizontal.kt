package dev.yamh.io.presentation.core.ui.source.kit.atom.stack

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.MouseScrollDown32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.MouseScrollUp32
import dev.yamh.presentation.core.styling.core.Theme
import kotlin.math.abs

@Composable
public fun StackHorizontalItemContainer(
    modifier: Modifier = Modifier,
    card: @Composable () -> Unit
) {
    var rotation by remember { mutableStateOf(0f) }
    Layout(
        content = card,
        modifier = modifier
            .graphicsLayer { cameraDistance = 12f * density }
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress { change, dragAmount ->
                    rotation += dragAmount.y * 0.1f
                    change.consume()
                }
            }
    ) { measurables, constraints ->
        val placeable = measurables.first().measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }
}

@DslMarker
public annotation class StackHorizontalScopeMarker

@StackHorizontalScopeMarker
public interface StackHorizontalScope {
    public fun item(key: Any? = null, content: @Composable (selected: Boolean) -> Unit)
    public fun <T> items(
        data: List<T>,
        key: ((index: Int, item: T) -> Any)? = null,
        itemContent: @Composable (index: Int, item: T, selected: Boolean) -> Unit
    )
}

private class StackHorizontalScopeImpl : StackHorizontalScope {
    data class Entry(val key: Any?, val content: @Composable (Boolean) -> Unit)

    val entries = mutableListOf<Entry>()
    override fun item(key: Any?, content: @Composable (selected: Boolean) -> Unit) {
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

//@Composable
//public fun StackHorizontal(
//    modifier: Modifier = Modifier,
//    stackedState: Boolean = false,
//    paddingBetweenItems: Dp = 42.dp,
//    paddingVerticalDefault: Dp = 32.dp,
//    paddingVerticalSelected: Dp = 16.dp,
//    visibleItems: Int = 3,
//    content: StackHorizontalScope.() -> Unit,
//) {
//    val scope = StackHorizontalScopeImpl().apply(content)
//    val cardOffsetX by animateDpAsState(if (stackedState) 20.dp else 0.dp, label = "")
//    val cardScale by animateFloatAsState(if (stackedState) 0.95f else 1f, label = "")
//    val density = LocalDensity.current
//
//    var firstVisible by remember { mutableIntStateOf(0) }
//    val lastPossibleFirst = (scope.entries.size - visibleItems).coerceAtLeast(0)
//    var selectedCard by remember { mutableIntStateOf(-1) }
//    var direction by remember { mutableStateOf(MouseScrollUp32) }
//
//    Row(
//        modifier = modifier.fillMaxSize(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Center
//    ) {
//        BoxWithConstraints(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val height = maxHeight
//            val width = height * 1.5f
//            val window = scope.entries.drop(firstVisible).take(visibleItems)
//
//            window.forEachIndexed { localIndex, entry ->
//                val centerOffset = this@BoxWithConstraints.maxWidth / 2f - width / 2f
//                val offsetX by animateDpAsState(
//                    targetValue = if (localIndex == selectedCard) -centerOffset
//                    else (-16).dp + (localIndex * paddingBetweenItems.value).dp,
//                    animationSpec = tween(500),
//                    label = "offsetX"
//                )
//
//                val verticalPadding by animateDpAsState(
//                    targetValue = if (selectedCard == localIndex)
//                        paddingVerticalSelected else paddingVerticalDefault,
//                    label = "pad"
//                )
//
//                val interactionSource = remember { MutableInteractionSource() }
//
//                StackHorizontalItemContainer(
//                    modifier = Modifier
//                        .offset(x = offsetX)
//                        .fillMaxHeight()
//                        .aspectRatio(1.5f)
//                        .graphicsLayer {
//                            translationX = with(density) { cardOffsetX.toPx() * localIndex }
//                            scaleX = cardScale
//                            scaleY = cardScale
//                        }
//                        .padding(vertical = verticalPadding)
//                        .clickable(interactionSource, indication = null) {
//                            selectedCard = if (selectedCard == localIndex) -1 else localIndex
//                        }
//                        .onHorizontalSwipe(
//                            onSwipeLeft = {
//                                if (selectedCard != -1) return@onHorizontalSwipe
//                                if (firstVisible < lastPossibleFirst) {
//                                    firstVisible += 1
//                                    selectedCard = -1
//                                } else direction = MouseScrollUp32
//                            },
//                            onSwipeRight = {
//                                if (selectedCard != -1) return@onHorizontalSwipe
//                                if (firstVisible > 0) {
//                                    firstVisible -= 1
//                                    selectedCard = -1
//                                } else direction = MouseScrollDown32
//                            }
//                        ),
//                ) {
//                    entry.content(selectedCard == localIndex)
//                }
//            }
//
//            if (scope.entries.size > visibleItems && (selectedCard == -1)) {
//                Icon(
//                    imageVector = direction,
//                    contentDescription = null,
//                    tint = Theme.color.primary1,
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .offset(x = 32.dp)
//                        .size(32.dp)
//                )
//            }
//        }
//    }
//}

//@Composable
//public fun StackHorizontal(
//    modifier: Modifier = Modifier,
//    stackedState: Boolean = false,
//    paddingBetweenItems: Dp = 42.dp,
//    paddingVerticalDefault: Dp = 32.dp,
//    paddingVerticalSelected: Dp = 16.dp,
//    visibleItems: Int = 3,
//    content: StackHorizontalScope.() -> Unit,
//) {
//    val scope = StackHorizontalScopeImpl().apply(content)
//    val cardOffsetX by animateDpAsState(if (stackedState) 20.dp else 0.dp, label = "")
//    val cardScale by animateFloatAsState(if (stackedState) 0.95f else 1f, label = "")
//    val density = LocalDensity.current
//
//    var firstVisible by remember { mutableIntStateOf(0) }
//    val lastPossibleFirst = (scope.entries.size - visibleItems).coerceAtLeast(0)
//    var selectedCard by remember { mutableIntStateOf(-1) }
//    var direction by remember { mutableStateOf(MouseScrollUp32) }
//
//    Row(
//        modifier = modifier.fillMaxSize(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.Start // start alignment
//    ) {
//        BoxWithConstraints(
//            contentAlignment = Alignment.CenterStart, // start baseline
//            modifier = Modifier.fillMaxSize()
//        ) {
//            val height = maxHeight
//            val width = height * 1.5f
//            val window = scope.entries.drop(firstVisible).take(visibleItems)
//            val totalWidth = width + paddingBetweenItems * (visibleItems - 1)
//
//            window.forEachIndexed { localIndex, entry ->
//                val baseOffset = (localIndex * paddingBetweenItems.value).dp
//                val selectedOffset = maxWidth - width - paddingBetweenItems
//
//                // animate Cancel32 offset based on selection state
//                val offsetX by animateDpAsState(
//                    targetValue = if (localIndex == selectedCard) selectedOffset else baseOffset,
//                    animationSpec = tween(500),
//                    label = "offsetX"
//                )
//
//                val verticalPadding by animateDpAsState(
//                    targetValue = if (selectedCard == localIndex)
//                        paddingVerticalSelected else paddingVerticalDefault,
//                    label = "pad"
//                )
//
//                val interactionSource = remember { MutableInteractionSource() }
//
//                StackHorizontalItemContainer(
//                    modifier = Modifier
//                        .offset(x = offsetX)
//                        .fillMaxHeight()
//                        .aspectRatio(1.5f)
//                        .graphicsLayer {
//                            translationX = with(density) { cardOffsetX.toPx() * localIndex }
//                            scaleX = cardScale
//                            scaleY = cardScale
//                        }
//                        .padding(vertical = verticalPadding)
//                        .clickable(interactionSource, indication = null) {
//                            selectedCard = if (selectedCard == localIndex) -1 else localIndex
//                        }
//                        .onVerticalSwipe(
//                            onSwipeUp = {
//                                if (selectedCard != -1) return@onVerticalSwipe
//                                if (firstVisible < lastPossibleFirst) {
//                                    firstVisible += 1
//                                    selectedCard = -1
//                                } else direction = MouseScrollUp32
//                            },
//                            onSwipeDown = {
//                                if (selectedCard != -1) return@onVerticalSwipe
//                                if (firstVisible > 0) {
//                                    firstVisible -= 1
//                                    selectedCard = -1
//                                } else direction = MouseScrollUp32
//                            }
//                        ),
//                ) {
//                    entry.content(selectedCard == localIndex)
//                }
//            }
//
//            if (scope.entries.size > visibleItems && (selectedCard == -1)) {
//                Icon(
//                    imageVector = direction,
//                    contentDescription = null,
//                    tint = Theme.color.primary1,
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .offset(x = 32.dp)
//                        .size(32.dp)
//                )
//            }
//        }
//    }
//}

@Composable
public fun StackHorizontal(
    modifier: Modifier = Modifier,
    stackedState: Boolean = false,
    paddingBetweenItems: Dp = 42.dp,
    paddingHorizontalDefault: Dp = 32.dp,
    paddingHorizontalSelected: Dp = 16.dp,
    visibleItems: Int = 3,
    content: StackHorizontalScope.() -> Unit,
) {
    val scope = StackHorizontalScopeImpl().apply(content)
    val cardOffsetY by animateDpAsState(if (stackedState) 20.dp else 0.dp, label = "")
    val cardScale by animateFloatAsState(if (stackedState) 0.95f else 1f, label = "")
    val density = LocalDensity.current

    var firstVisible by remember { mutableIntStateOf(0) }
    val lastPossibleFirst = (scope.entries.size - visibleItems).coerceAtLeast(0)
    var selectedCard by remember { mutableIntStateOf(-1) }
    var direction by remember { mutableStateOf(MouseScrollUp32) }

    val spacingMultiplier = 1.25f // більша відстань між картками

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxSize()
        ) {
            val width = maxWidth
            val height = width / 1.5f
            val window = scope.entries.drop(firstVisible).take(visibleItems)

            window.forEachIndexed { localIndex, entry ->
                // трохи більший відступ між картками
                val baseOffsetY =
                    (-16).dp + (localIndex * paddingBetweenItems.value * spacingMultiplier).dp

                // вибрана картка їде до самого кінця правої сторони
                val targetX = if (localIndex == selectedCard) {
                    width - (height / 1.5f) - paddingHorizontalSelected
                } else 0.dp

                val offsetX by animateDpAsState(
                    targetValue = targetX,
                    animationSpec = tween(600, easing = LinearOutSlowInEasing),
                    label = "offsetX"
                )

                val offsetY by animateDpAsState(
                    targetValue = baseOffsetY,
                    animationSpec = tween(600, easing = LinearOutSlowInEasing),
                    label = "offsetY"
                )

                val horizontalPadding by animateDpAsState(
                    targetValue = if (selectedCard == localIndex)
                        paddingHorizontalSelected else paddingHorizontalDefault,
                    label = "pad"
                )

                val interactionSource = remember { MutableInteractionSource() }

                StackHorizontalItemContainer(
                    modifier = Modifier
                        .offset(x = offsetX, y = offsetY)
                        .fillMaxHeight()
                        .aspectRatio(1.5f)
                        .graphicsLayer {
                            translationY = with(density) { cardOffsetY.toPx() * localIndex }
                            scaleX = cardScale
                            scaleY = cardScale
                        }
                        .padding(horizontal = horizontalPadding)
                        .clickable(interactionSource, indication = null) {
                            selectedCard = if (selectedCard == localIndex) -1 else localIndex
                        }
                        .onHorizontalSwipe(
                            onSwipeUp = {
                                if (selectedCard != -1) return@onHorizontalSwipe
                                if (firstVisible < lastPossibleFirst) {
                                    firstVisible += 1
                                    selectedCard = -1
                                } else direction = MouseScrollUp32
                            },
                            onSwipeDown = {
                                if (selectedCard != -1) return@onHorizontalSwipe
                                if (firstVisible > 0) {
                                    firstVisible -= 1
                                    selectedCard = -1
                                } else direction = MouseScrollDown32
                            }
                        ),
                ) {
                    entry.content(selectedCard == localIndex)
                }
            }

            if (scope.entries.size > visibleItems && selectedCard == -1) {
                Icon(
                    imageVector = direction,
                    contentDescription = null,
                    tint = Theme.color.primary1,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = 32.dp)
                        .size(32.dp)
                )
            }
        }
    }
}


private fun Modifier.onHorizontalSwipe(
    minDistance: Dp = 64.dp,
    minVelocity: Float = 1200f,
    consume: Boolean = true,
    onSwipeUp: () -> Unit,
    onSwipeDown: () -> Unit
): Modifier = this.then(
    Modifier.pointerInput(minDistance, minVelocity, consume) {
        val minDistancePx = minDistance.toPx()
        awaitEachGesture {
            val down = awaitFirstDown(requireUnconsumed = false)
            val id = down.id
            var totalDy = 0f
            val vt = VelocityTracker().apply { addPosition(down.uptimeMillis, down.position) }
            var current: PointerInputChange = down
            while (true) {
                val event = awaitPointerEvent(PointerEventPass.Main)
                val change = event.changes.firstOrNull { it.id == id } ?: continue
                val dy = change.position.y - change.previousPosition.y
                totalDy += dy
                vt.addPosition(change.uptimeMillis, change.position)
                if (consume && change.pressed) change.consumePositionChange()
                if (!change.pressed) {
                    val vy = vt.calculateVelocity().y
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

@Composable
public fun StackHorizontalAnimated(
    modifier: Modifier = Modifier,
    stackedState: Boolean = false,
    paddingBetweenItems: Dp = 56.dp,
    paddingHorizontalDefault: Dp = 32.dp,
    paddingHorizontalSelected: Dp = 16.dp,
    visibleItems: Int = 3,
    content: StackHorizontalScope.() -> Unit,
) {
    val scope = remember { StackHorizontalScopeImpl() }.apply(content)

    var firstVisible by remember { mutableIntStateOf(0) }
    val lastPossibleFirst = ((scope.entries).distinct().size - visibleItems).coerceAtLeast(0)
    var selectedCard by remember { mutableIntStateOf(-1) }
    var direction by remember { mutableStateOf(MouseScrollUp32) }
    var slideDir by remember { mutableStateOf<SlideDir?>(null) }

    val cardOffsetY by animateDpAsState(if (stackedState) 20.dp else 0.dp, label = "")
    val cardScale by animateFloatAsState(if (stackedState) 0.95f else 1f, label = "")
    val density = LocalDensity.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        BoxWithConstraints(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier.fillMaxSize()
        ) {
            val width = maxWidth
            val height = width / 1.5f
            val stackHeight = height + paddingBetweenItems * (visibleItems - 1)
            val stackHeightPx = with(density) { stackHeight.roundToPx() }

            val fade = 150
            val scale = 250
            val slide = 500
            val delay = 50

            AnimatedContent(
                targetState = firstVisible,
                label = "stack-horizontal",
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
                            slideInVertically(tween(slide, delay)) { +stackHeightPx / 2 }) togetherWith
                            (fadeOutAnim + scaleOutAnim +
                                    slideOutVertically(tween(slide, delay)) { +stackHeightPx / 2 })

                    when (slideDir) {
                        SlideDir.Up -> up
                        SlideDir.Down -> down
                        else -> fadeIn() togetherWith fadeOut()
                    }.using(SizeTransform(clip = false))
                },
                contentKey = { it }
            ) { first ->
                val window = (scope.entries.distinct()).drop(first).take(visibleItems)

                window.forEachIndexed { localIndex, entry ->
                    val baseOffsetY = (-16).dp + (localIndex * paddingBetweenItems.value).dp

                    val offsetY by animateDpAsState(
                        targetValue = baseOffsetY,
                        animationSpec = tween(500),
                        label = "offsetY"
                    )
                    val horizontalPadding by animateDpAsState(
                        targetValue = if (selectedCard == localIndex)
                            paddingHorizontalSelected else paddingHorizontalDefault,
                        label = "pad"
                    )

                    val targetX = if (localIndex == selectedCard) {
                        width - (height / 1.5f) - paddingHorizontalSelected
                    } else 0.dp

                    val offsetX by animateDpAsState(
                        targetValue = targetX,
                        animationSpec = tween(600, easing = LinearOutSlowInEasing),
                        label = "offsetX"
                    )

                    val interactionSource = remember { MutableInteractionSource() }

                    StackHorizontalItemContainer(
                        modifier = Modifier
                            .offset(x = offsetX, y = offsetY)
                            .fillMaxHeight()
                            .aspectRatio(1.5f)
                            .graphicsLayer {
                                translationY = with(density) { cardOffsetY.toPx() * localIndex }
                                scaleX = cardScale
                                scaleY = cardScale
                            }
                            .padding(horizontal = horizontalPadding)
                            .clickable(interactionSource, indication = null) {
                                selectedCard =
                                    if (selectedCard == localIndex) -1 else localIndex
                            }
                            .onHorizontalSwipe(
                                onSwipeUp = {
                                    if (selectedCard != -1) return@onHorizontalSwipe
                                    if (firstVisible < lastPossibleFirst) {
                                        slideDir = SlideDir.Up
                                        firstVisible += visibleItems
                                        selectedCard = -1
                                    } else direction = MouseScrollUp32
                                },
                                onSwipeDown = {
                                    if (selectedCard != -1) return@onHorizontalSwipe
                                    if (firstVisible > 0) {
                                        slideDir = SlideDir.Down
                                        firstVisible -= visibleItems
                                        selectedCard = -1
                                    } else direction = MouseScrollDown32
                                }
                            ),
                    ) {
                        entry.content(selectedCard == localIndex)
                    }
                }
            }

            if (scope.entries.size > visibleItems && selectedCard == -1) {
                Icon(
                    imageVector = direction,
                    contentDescription = null,
                    tint = Theme.color.primary1,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = 32.dp)
                        .size(32.dp)
                )
            }
        }
    }
}