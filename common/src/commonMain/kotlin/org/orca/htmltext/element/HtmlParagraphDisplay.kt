package org.orca.htmltext.element

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val HtmlParagraph = HtmlElementRenderer { element, modifier ->
    HtmlParagraphDisplay(modifier) {
        HtmlChildRenderer(element)
    }
}

/**
 * Helper function that displays [content] consistent to the way HtmlText displays paragraphs.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HtmlParagraphDisplay(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        content()
    }
}
