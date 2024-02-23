package com.example.tensorflowlite

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class GestureRecognitionActivity : AppCompatActivity() {
    private lateinit var gestureImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ImageView 참조
        gestureImageView = findViewById(R.id.gestureImageView)
        // 모델 예측 결과를 ImageView에 표시하는 함수 호출
        displayGestureResult()
    }

    private fun displayGestureResult() {
        // 모델에서 예측한 이미지를 가져와서 ImageView에 설정
        val gestureBitmap: Bitmap = getGestureBitmap() // 모델에서 가져오는 함수를 사용해야 함
        gestureImageView.setImageBitmap(gestureBitmap)
    }
}
