package org.orca.htmltext.example

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(
    title = "HtmlText Example"
) {
    App()
}

@Preview
@Composable
private fun LightHtmlTextPreview() {
    MaterialTheme(lightColorScheme()) {
        Surface(Modifier.fillMaxSize()) {
            HtmlExampleContent()
        }
    }
}

@Preview
@Composable
private fun DarkHtmlTextPreview() {
    MaterialTheme(darkColorScheme()) {
        Surface(Modifier.fillMaxSize()) {
            HtmlExampleContent()
        }
    }
}
