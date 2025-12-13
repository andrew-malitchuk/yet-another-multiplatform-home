package dev.yamh.io.presentation.core.ui.core.ext

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random
import kotlin.random.nextInt
import androidx.annotation.FloatRange
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.takeOrElse
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.hypot
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.runtime.*
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import kotlin.math.hypot
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.launch

/**
 * Creates a new modifier that applies a clickable behavior to the composable without the
 * default ripple effect.
 *
 * @param onClick An optional lambda function that defines the action to be performed when the
 *        user clicks on the composable. Can be null if click behavior is not desired.
 * @return A new `Modifier` instance with the clickable behavior applied (if `onClick` is not
 *         null) or the original modifier unchanged (if `onClick` is null).
 */
public fun Modifier.noRippleClickable(onClick: (() -> Unit)? = null): Modifier =
    composed {
        if (onClick != null) {
            this.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                onClick.invoke()
            }
        } else {
            this
        }
    }

public fun Modifier.noRippleLongClickable(onClick: (() -> Unit)? = null): Modifier =
    composed {
        if (onClick != null) {
            this.pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onClick.invoke() }
                )
            }
        } else {
            this
        }
    }

@Composable
public fun Modifier.animatedBorder(
    borderColors: List<Color>,
    backgroundColor: Color,
    shape: Shape = RectangleShape,
    borderWidth: Dp = 1.dp,
    animationDurationInMillis: Int = 1000,
    easing: Easing = LinearEasing
): Modifier {
    val brush = Brush.sweepGradient(borderColors)
    val infiniteTransition = rememberInfiniteTransition(label = "animatedBorder")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDurationInMillis, easing = easing),
            repeatMode = RepeatMode.Restart
        ), label = "angleAnimation"
    )

    return this
        .clip(shape)
        .padding(borderWidth)
        .drawWithContent {
            rotate(angle) {
                drawCircle(
                    brush = brush,
                    radius = size.width,
                    blendMode = BlendMode.SrcIn,
                )
            }
            drawContent()
        }
        .background(color = backgroundColor, shape = shape)
}


/***
 *
 *
 * __Example of use:__
 *
 * ```
 * @Composable
 * public fun GlitchEffectImpl() {
 *
 *     var key by remember { mutableStateOf(0) }
 *     val interaction = remember { MutableInteractionSource() }
 *     val isHovered by interaction.collectIsHoveredAsState()
 *
 *     Box(
 *         modifier = Modifier
 *             .pointerInput(Unit) {
 *                 detectTapGestures(
 *                     onTap = {
 *                         key = Random.nextInt()
 *                     }
 *                 )
 *             }
 *             .pointerHoverIcon(PointerIcon.Hand)
 *             .hoverable(interaction)
 *             .glitchEffect(
 *                 key,
 *                 remember { listOf(Color.Cyan, Color.Red, Color.Green) })
 *             .padding(4.dp)
 *             .padding(horizontal = 32.dp, vertical = 16.dp)
 *     ) {
 *         Text(
 *             text = "Tap to Glitch",
 *             color = Color.Cyan
 *         )
 *     }
 *
 * }
 * ```
 */
@Composable
public fun Modifier.glitchEffect(
    key: Any? = null,
    glitchColors: List<Color>,
    slices: Int = 20,
): Modifier {

    val graphicsLayer = rememberGraphicsLayer()
    var step by remember { mutableStateOf(0) }

    LaunchedEffect(key) {
        Animatable(10f)
            .animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearEasing,
                )
            ) {
                step = this.value.roundToInt()
            }
    }

    return drawWithContent {
        if (step == 0) {
            drawContent()
            return@drawWithContent
        }
        graphicsLayer.record { this@drawWithContent.drawContent() }

        val intensity = step / 10f
        for (i in 0 until slices) {
            translate(
                left = if (Random.nextInt(5) < step)
                    Random.nextInt(-20..20).toFloat() * intensity
                else
                    0f
            ) {
                scale(
                    scaleY = 1f,
                    scaleX = if (Random.nextInt(10) < step)
                        1f + (1f * Random.nextFloat() * intensity)
                    else
                        1f
                ) {
                    clipRect(
                        top = (i / slices.toFloat()) * size.height,
                        bottom = (((i + 1) / slices.toFloat()) * size.height) + 1f,
                    ) {
//                        layer {
                        drawLayer(graphicsLayer)
                        if (Random.nextInt(5, 30) < step) {
                            drawRect(
                                color = glitchColors.random(),
                                blendMode = BlendMode.SrcAtop
                            )
                        }
//                        }
                    }
                }
            }
        }
    }
}

/**
 * Circular reveal that covers the whole container with [revealColor].
 * Starts at the tap position. When animation finishes, [onRevealed] is invoked.
 */
public fun Modifier.circularRevealOnTap(
    revealColor: Color,
    durationMillis: Int = 550,
    easing: (Float) -> Float = { it }, // you can swap in FastOutSlowInEasing if you must
    enabled: Boolean = true,
    onRevealed: (tapOffset: Offset) -> Unit,
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val progress = remember { Animatable(0f) }
    var center by remember { mutableStateOf(Offset.Unspecified) }
    var size by remember { mutableStateOf(IntSize.Zero) }
    var animating by remember { mutableStateOf(false) }

    val draw = Modifier
        .drawWithContent {
            drawContent()
            if (animating && size.width > 0 && size.height > 0 && center.isSpecified) {
                val maxR = hypot(size.width.toFloat(), size.height.toFloat())
                drawCircle(
                    color = revealColor,
                    radius = progress.value * maxR,
                    center = center
                )
            }
        }
        .onGloballyPositioned { coords -> size = coords.size }

    val input = if (!enabled) Modifier else Modifier.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val down = awaitFirstDown(requireUnconsumed = false)
                if (animating) continue
                center = down.position
                animating = true

                scope.launch {
                    progress.snapTo(0f)
                    progress.animateTo(
                        1f,
                        animationSpec = tween(durationMillis, easing = easing)
                    )
                    animating = false
                    onRevealed(center)
                }
            }
        }
    }

    draw.then(input)
}