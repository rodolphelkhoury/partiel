package com.example.partiel

import android.graphics.Paint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun ThirdPage(
    firstName: String,
    lastName: String,
    number: Int,
    navController: NavController
) {
    var isSpinning by remember { mutableStateOf(false) }
    var rotationValue by remember { mutableStateOf(0f) }
    var resultNumber by remember { mutableStateOf(0) }
    var hasResult by remember { mutableStateOf(false) }
    var isWinner by remember { mutableStateOf(false) }

    val sections = 12

    val colors = listOf(
        Color(0xFFE57373),
        Color(0xFF81C784),
        Color(0xFF64B5F6),
        Color(0xFFFFD54F),
        Color(0xFFBA68C8),
        Color(0xFF4FC3F7),
        Color(0xFFAED581),
        Color(0xFFFFB74D),
        Color(0xFF4DB6AC),
        Color(0xFFF06292),
        Color(0xFF9575CD),
        Color(0xFFFF8A65)
    )

    val rotation by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = if (isSpinning) 3000 else 0,
            easing = LinearEasing
        ),
        finishedListener = {
            if (isSpinning) {
                isSpinning = false
                hasResult = true

                val normalizedRotation = (rotationValue % 360)
                val sectionSize = 360f / sections

                var pointerPosition = (270 - normalizedRotation) % 360
                if (pointerPosition < 0) pointerPosition += 360
                val sectionIndex = (pointerPosition / sectionSize).toInt()

                resultNumber = sectionIndex + 1

                isWinner = (resultNumber == number)
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Hello $firstName $lastName!, you chose $number",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Wheel of Fortune
        Box(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(250.dp)
                    .rotate(rotation)
            ) {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = size.width / 2

                // Draw the wheel sections
                val sectionAngle = 360f / sections
                for (i in 0 until sections) {
                    val startAngle = i * sectionAngle

                    drawArc(
                        color = colors[i % colors.size],
                        startAngle = startAngle,
                        sweepAngle = sectionAngle,
                        useCenter = true,
                        topLeft = Offset(0f, 0f),
                        size = Size(size.width, size.height)
                    )

                    drawContext.canvas.nativeCanvas.apply {
                        val textPaint = Paint().apply {
                            color = Color.White.toArgb()
                            textSize = 24f
                            textAlign = Paint.Align.CENTER
                            isFakeBoldText = true
                        }

                        val angle = Math.toRadians((startAngle + sectionAngle / 2).toDouble())
                        val textRadius = radius * 0.7f

                        val x = centerX + (cos(angle) * textRadius).toFloat()
                        val y = centerY + (sin(angle) * textRadius).toFloat()

                        save()
                        rotate(startAngle + sectionAngle / 2, x, y)
                        rotate(90f, x, y)

                        val textHeight = textPaint.fontMetrics.descent - textPaint.fontMetrics.ascent
                        val textOffset = textHeight / 2 - textPaint.fontMetrics.descent

                        drawText("${i + 1}", x, y + textOffset, textPaint)
                        restore()
                    }
                }

                drawCircle(
                    color = Color.Black,
                    radius = radius,
                    style = Stroke(width = 4f)
                )
            }

            Canvas(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopCenter)
            ) {
                val pointerPath = androidx.compose.ui.graphics.Path().apply {
                    moveTo(size.width / 2, 0f)
                    lineTo(0f, size.height)
                    lineTo(size.width, size.height)
                    close()
                }
                drawPath(
                    path = pointerPath,
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (hasResult) {
            Text(
                text = if (isWinner) "Congratulations! You won!" else "Sorry, you got $resultNumber. Try again!",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = if (isWinner) Color.Green else Color.Red,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (!isSpinning) {
                    isSpinning = true
                    hasResult = false
                    rotationValue += 1800f + Random.nextFloat() * 1080f
                }
            },
            enabled = !isSpinning
        ) {
            Text(if (hasResult) "Spin Again" else "Spin")
        }
    }
}