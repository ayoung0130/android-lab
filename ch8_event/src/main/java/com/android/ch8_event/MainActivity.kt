package com.android.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.android.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var initTime = 0L
    private var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            with(binding) {
                chronometer.base = SystemClock.elapsedRealtime() + pauseTime
                chronometer.start()
                btnStop.isEnabled = true
                btnReset.isEnabled = true
                btnStart.isEnabled = false
            }
        }
        binding.btnStop.setOnClickListener {
            with(binding) {
                pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
                chronometer.stop()
                btnStop.isEnabled = false
                btnReset.isEnabled = true
                btnStart.isEnabled = true
            }
        }
        binding.btnReset.setOnClickListener {
            pauseTime = 0L
            with(binding) {
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.stop()
                btnStop.isEnabled = false
                btnReset.isEnabled = false
                btnStart.isEnabled = true
            }
        }
    }

    // 뒤로가기 버튼 이벤트 핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode === KeyEvent.KEYCODE_BACK) {
            // 뒤로가기 버튼을 처음 눌렀거나 누른 지 3초 지났을 때 처리
            if (System.currentTimeMillis() - initTime > 3000) {
                Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }
}