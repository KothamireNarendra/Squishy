import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput


fun Modifier.squishy(
    color: Color,
    borderColor: Color = Color.Transparent,
    onClick: (() -> Unit)? = null
): Modifier = composed {

    val springSpec = remember {
        SpringSpec<Float>(
            stiffness = 400f,
            dampingRatio = 0.2f
        )
    }

    val animSpec = remember {
        TweenSpec<Float>(
            durationMillis = 50,
            easing = LinearEasing
        )
    }

    val path = remember { Path() }
    val curveAmt = remember { Animatable(0f) }

    this
        .drawBehind {
            val height = size.height
            val width = size.width
            val fracOfHeight = height / 5
            val fracOfWidth = width / 5
            val rad = 30f
            curveAmt.value
            path.apply {
                path.reset()
                moveTo(0f, 0f + rad)
                quadraticBezierTo(0f, 0f, rad, 0f)
                cubicTo(
                    rad + fracOfWidth,
                    curveAmt.value,
                    width - rad - fracOfWidth,
                    curveAmt.value,
                    width - rad,
                    0f
                )
                quadraticBezierTo(width, 0f, width, rad)
                cubicTo(
                    width - curveAmt.value,
                    rad + fracOfHeight,
                    width - curveAmt.value,
                    height - rad - fracOfHeight,
                    width,
                    height - rad
                )
                quadraticBezierTo(width, height, width - rad, height)
                cubicTo(
                    width - rad - fracOfWidth,
                    height - curveAmt.value,
                    rad + fracOfWidth,
                    height - curveAmt.value,
                    rad,
                    height
                )
                quadraticBezierTo(0f, height, 0f, height - rad)
                cubicTo(
                    curveAmt.value,
                    height - rad - fracOfHeight,
                    curveAmt.value,
                    rad + fracOfHeight,
                    0f,
                    rad,
                )
            }
            if (borderColor != Color.Transparent) {
                drawPath(path = path, style = Stroke(width = 8f), color = borderColor)
            }
            if (color != Color.Transparent) {
                drawPath(path = path, style = Fill, color = color)
            }
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    try {
                        curveAmt.animateTo(25f, animSpec)
                        awaitRelease()
                    } finally {
                        curveAmt.stop()
                        curveAmt.snapTo(25f)
                        curveAmt.animateTo(0f, springSpec)
                        onClick?.invoke()
                    }
                }
            )
        }
}
