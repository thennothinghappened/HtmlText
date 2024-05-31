package org.orca.htmltext.element

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import org.jsoup.nodes.Element

/**
 * Renderer that just creates a line break.
 */
val HtmlLineBreak = HtmlElementRenderer { _, _ ->
    Spacer(Modifier.fillMaxWidth())
}
