package org.orca.htmltext.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import org.jsoup.nodes.Element
import org.orca.htmltext.util.validateUrl

@Composable
internal fun HtmlImage(
    node: Element,
    modifier: Modifier = Modifier,
    domain: String? = null
) {
    val source = node.attr("src")
    if (source == "") return

    val url = validateUrl(source, domain) ?: return

    KamelImage(
        lazyPainterResource(url),
        node.attr("alt") ?: "No description.",
        modifier,
        contentScale = ContentScale.Inside
    )
}