package org.orca.htmltext.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
internal fun HtmlLineBreak() {
    Spacer(Modifier.fillMaxWidth())
}

@Composable
internal fun HtmlHardLineBreak(style: TextStyle = TextStyle()) {
    Text("\n")
}