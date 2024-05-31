package org.orca.htmltext.element

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jsoup.nodes.Element

/**
 * Renderer for a HTML element, which renders given a JSoup [Element].
 */
fun interface HtmlElementRenderer {

    /**
     * Render the given [Element].
     *
     * @param element The JSoup element to render.
     * @param modifier Modifier on the element.
     */
    @Composable
    fun Render(
        element: Element,
        modifier: Modifier
    )

}
