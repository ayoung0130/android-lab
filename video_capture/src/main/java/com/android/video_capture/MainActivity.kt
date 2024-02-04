package com.android.video_capture

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.impl.utils.futures.FutureCallback
import androidx.camera.core.impl.utils.futures.Futures
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.lifecycle.LifecycleOwner
import com.android.video_capture.databinding.ActivityMainBinding
import com.google.common.util.concurrent.ListenableFuture
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.util.concurrent.MoreExecutors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupCamera()
    }

    @SuppressLint("RestrictedApi")
    private fun setupCamera() {
        val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(this)

        val callback = object : FutureCallback<ProcessCameraProvider> {
            @SuppressLint("RestrictedApi")
            override fun onSuccess(cameraProvider: ProcessCameraProvider?) {
                if (cameraProvider != null) {
                    // 성공적으로 카메라 프로바이더를 얻었을 때 수행할 작업
                    initializeCamera(cameraProvider)
                }
            }

            @SuppressLint("RestrictedApi")
            override fun onFailure(t: Throwable) {
                // 실패할 경우의 작업
                Log.e(TAG, "Error initializing camera provider", t)
            }
        }

        // 콜백을 이용하여 비동기 작업 완료 후 작업 수행
        Futures.addCallback(cameraProviderFuture, callback, MoreExecutors.directExecutor())
    }

    private fun initializeCamera(cameraProvider: ProcessCameraProvider) {
        val preview = Preview.Builder().build()
        val cameraView = binding.cameraView
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            // 카메라에 미리보기 추가
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this as LifecycleOwner,
                cameraSelector,
                preview
            )
            cameraView
        } catch (exc: Exception) {
            Log.e(TAG, "Error initializing camera", exc)
        }
    }

}