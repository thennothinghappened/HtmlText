package org.orca.htmltext.element

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import org.orca.htmltext.util.validateUrl

/**
 * Renderer for a HTML anchor/link.
 */
val HtmlTextAnchor = HtmlElementRenderer { element, modifier ->

    val uriHandler = LocalUriHandler.current
    val href = element.attr("abs:href")

    validateUrl(href)
        ?.let {
            ProvideTextStyle(TextStyle(color = MaterialTheme.colorScheme.primary)) {
                HtmlTextUnderline.Render(element, modifier.clickable { uriHandler.openUri(it) })
            }
        }
        ?: HtmlChildRenderer(element, modifier)

}
