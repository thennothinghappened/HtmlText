package org.orca.htmltext.element

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

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
    val lineColor = DividerDefaults.color

    Row(
        Modifier
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
            .drawBehind {
                drawLine(
                    color = lineColor,
                    start = Offset.Zero,
                    end = Offset(0f, size.height),
                    strokeWidth = 4f
                )
            }
            .then(modifier)
    ) {
        HtmlParagraphDisplay(
            Modifier.padding(start = 16.dp),
            content
        )
    }
}
