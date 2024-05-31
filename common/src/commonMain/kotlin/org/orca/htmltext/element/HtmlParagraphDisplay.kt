package org.orca.htmltext.element

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow

val HtmlParagraph = HtmlElementRenderer { element, modifier ->
    HtmlParagraphDisplay(modifier) {
        HtmlChildRenderer(element)
    }
}

/**
 * Helper function that displays [content] consistent to the way HtmlText displays paragraphs.
 */
@Composable
fun HtmlParagraphDisplay(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp),
        content = content
    )
}
