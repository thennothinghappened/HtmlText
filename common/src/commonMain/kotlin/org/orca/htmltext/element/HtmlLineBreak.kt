package org.orca.htmltext.element

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

/**
 * Renderer that just creates a line break.
 */
val HtmlLineBreak = HtmlElementRenderer { _, _ ->
    Spacer(Modifier.fillMaxWidth())
}
