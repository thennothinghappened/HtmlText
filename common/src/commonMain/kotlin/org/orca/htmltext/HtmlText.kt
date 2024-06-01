package org.orca.htmltext

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.*
import org.jsoup.Jsoup
import org.orca.htmltext.element.*

/**
 * Render a HTML document in Compose!
 *
 * @param document The HTML document to render.
 * @param modifier Modifier to apply to the base [FlowRow] container.
 * @param style The default text style to use for rendering the HTML document.
 * @param baseUri Base URI to use when resolving relative URIs which have no specified domain, i.e., `/something...`
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HtmlText(
    document: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    baseUri: String = ""
) {
    ProvideTextStyle(style) {
        FlowRow(modifier) {
            HtmlChildRenderer(Jsoup.parse(document, baseUri))
        }
    }
}
