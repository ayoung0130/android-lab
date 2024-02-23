package com.example.tensorflowlite

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

fun getGestureBitmap(): Bitmap{
    // 예시로 더미 Bitmap을 반환
    val dummyBitmap = Bitmap.createBitmap(224, 224, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(dummyBitmap)
    val paint = Paint()
    paint.color = Color.RED
    canvas.drawRect(0f, 0f, 224f, 224f, paint)

    return dummyBitmap
}