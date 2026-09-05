package com.mesheures.app

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {

    private var uploadMessage: ValueCallback<Array<Uri>>? = null

    private val fileChooserLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.dataString?.let { uriString ->
                uploadMessage?.onReceiveValue(arrayOf(Uri.parse(uriString)))
            } ?: run {
                result.data?.clipData?.let { clipData ->
                    val uris = Array(clipData.itemCount) { i -> clipData.getItemAt(i).uri }
                    uploadMessage?.onReceiveValue(uris)
                }
            }
        } else {
            uploadMessage?.onReceiveValue(null)
        }
        uploadMessage = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.allowFileAccess = true
                        settings.cacheMode = WebSettings.LOAD_DEFAULT
                        webViewClient = WebViewClient()

                        // Permet à l'application d'ouvrir le sélecteur de fichiers natif d'Android
                        webChromeClient = object : WebChromeClient() {
                            override fun onShowFileChooser(
                                webView: WebView?,
                                filePathCallback: ValueCallback<Array<Uri>>?,
                                fileChooserParams: FileChooserParams?
                            ): Boolean {
                                uploadMessage?.onReceiveValue(null)
                                uploadMessage = filePathCallback
                                try {
                                    val intent = fileChooserParams?.createIntent()
                                    if (intent != null) {
                                        fileChooserLauncher.launch(intent)
                                        return true
                                    }
                                } catch (_: Exception) {
                                    uploadMessage = null
                                    return false
                                }
                                return false
                            }
                        }

                        loadUrl("file:///android_asset/index.html")
                    }
                }
            )
        }
    }
}
