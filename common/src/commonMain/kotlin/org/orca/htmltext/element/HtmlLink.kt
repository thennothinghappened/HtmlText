package org.orca.htmltext.element

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Element
import org.orca.htmltext.HtmlText
import org.orca.htmltext.util.validateUrl

/**
 * Renderer for a HTML anchor/link.
 */
val HtmlTextAnchor = HtmlElementRenderer { element, modifier ->

    val uriHandler = LocalUriHandler.current
    val href = element.attr("abs:href")

    validateUrl(href)
        ?.let { HtmlTextUnderline.Render(element, modifier.clickable { uriHandler.openUri(it) }) }
        ?: HtmlChildRenderer(element, modifier)

}
