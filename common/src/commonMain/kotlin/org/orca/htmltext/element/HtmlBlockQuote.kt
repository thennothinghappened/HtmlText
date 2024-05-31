package org.orca.htmltext.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.orca.htmltext.HtmlText

/**
 * Renderer for a block quote element, which indents a paragraph with a strip on the side.
 */
val HtmlTextBlockQuote = HtmlElementRenderer { element, modifier ->
    HtmlBlockQuote(modifier) {
        HtmlChildRenderer(element)
    }
}

@Composable
private fun HtmlBlockQuote(
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
        HtmlParagraphDisplay(
            Modifier.padding(start = 8.dp),
            content,
        )
    }
}
