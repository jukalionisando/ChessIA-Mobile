package com.jukaliao.chessia

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var web: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        val toolbar = LinearLayout(this)
        val analysis = Button(this).apply { text = "Tabuleiro / Análise" }
        val overlay = Button(this).apply { text = "Abrir flutuante" }
        toolbar.addView(analysis, LinearLayout.LayoutParams(0, 50, 1f))
        toolbar.addView(overlay, LinearLayout.LayoutParams(0, 50, 1f))
        web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            loadUrl("file:///android_asset/index.html")
        }
        root.addView(toolbar)
        root.addView(web, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
        analysis.setOnClickListener { web.evaluateJavascript("document.getElementById('boardPanel').scrollIntoView({behavior:'smooth'})", null) }
        overlay.setOnClickListener {
            if (!Settings.canDrawOverlays(this)) {
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")))
                Toast.makeText(this, "Ative a permissão 'Exibir sobre outros apps' e toque novamente.", Toast.LENGTH_LONG).show()
            } else {
                startService(Intent(this, OverlayService::class.java))
                moveTaskToBack(true)
            }
        }
    }
    override fun onDestroy() { web.destroy(); super.onDestroy() }
}
