package com.jukaliao.chessia

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView

class OverlayService : Service() {
    private lateinit var wm: WindowManager
    private var widget: TextView? = null
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val type = if (Build.VERSION.SDK_INT >= 26) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_PHONE
        val params = WindowManager.LayoutParams(205, 62, type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT).apply { gravity = Gravity.TOP or Gravity.END; x = 14; y = 150 }
        widget = TextView(this).apply {
            text = "♟ ChessIA\nToque para abrir análise"
            textSize = 13f
            setPadding(12, 8, 12, 8)
            setBackgroundColor(0xEE263747.toInt())
            setTextColor(0xFFFFFFFF.toInt())
            setOnClickListener { startActivity(Intent(this@OverlayService, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
        }
        wm.addView(widget, params)
    }
    override fun onDestroy() { widget?.let { wm.removeView(it) }; widget = null; super.onDestroy() }
}
