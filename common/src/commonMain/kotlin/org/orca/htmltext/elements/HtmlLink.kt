package org.orca.htmltext.elements

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

@Composable
internal fun HtmlLink(
    node: Element,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null
) {
    val href = node.attr("href")
    val url = validateUrl(href, domain)

    if (url == null) HtmlText(node, modifier, style, uriHandler, domain)
    else {
        HtmlText(
            node,
            modifier.clickable { uriHandler.openUri(url) },
            style.copy(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline)
        )
    }
}