package org.orca.htmltext.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
internal fun HtmlBlockQuote(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier
            .padding(start = 8.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(DividerDefaults.color)
        )
        HtmlParagraph(
            Modifier.padding(start = 8.dp),
            content,
        )
    }
}