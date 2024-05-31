package org.orca.htmltext.element

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import org.jsoup.nodes.Element
import org.orca.htmltext.util.validateUrl

/**
 * A HTML image `img` element.
 */
val HtmlImage = HtmlElementRenderer { element, modifier ->

    val source = element.attr("abs:src")
        .takeIf { it.isNotBlank() }
        ?.let { validateUrl(it) }
        ?: return@HtmlElementRenderer

    KamelImage(
        lazyPainterResource(source),
        element.attr("alt") ?: "No description.",
        modifier,
        contentScale = ContentScale.Inside
    )

}
