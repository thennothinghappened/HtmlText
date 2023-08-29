package org.orca.htmltext

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.google.accompanist.flowlayout.FlowRow
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import org.orca.htmltext.elements.*
import org.orca.htmltext.elements.HtmlBlockQuote
import org.orca.htmltext.elements.HtmlList
import org.orca.htmltext.elements.HtmlListType
import org.orca.htmltext.elements.HtmlParagraph

@Composable
fun HtmlText(
    document: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null
) {
    val html = Jsoup.parse(document)

    FlowRow(modifier) {
        HtmlText(html, style = style, uriHandler = uriHandler, domain = domain)
    }
}

@Composable
internal fun HtmlText(
    node: Node,
    modifier: Modifier = Modifier,
    style: TextStyle,
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null
) {
    node.childNodes().filterNot { it.toString().trim() == "" }.forEach {
        when (it) {
            is TextNode -> Text(text = it.text(), modifier = modifier, style = style)
            is Element -> {

                when (it.nodeName()) {
                    "strong" ->
                        HtmlText(it, modifier, style.copy(fontWeight = FontWeight.Bold), uriHandler, domain)
                    "b" ->
                        HtmlText(it, modifier, style.copy(fontWeight = FontWeight.Bold), uriHandler, domain)
                    "i" ->
                        HtmlText(it, modifier, style.copy(fontStyle = FontStyle.Italic), uriHandler, domain)
                    "em" ->
                        HtmlText(it, modifier, style.copy(fontStyle = FontStyle.Italic), uriHandler, domain)
                    "u" ->
                        HtmlText(it, modifier, style.copy(textDecoration = TextDecoration.Underline), uriHandler, domain)
                    "strike" ->
                        HtmlText(it, modifier, style.copy(textDecoration = TextDecoration.LineThrough), uriHandler, domain)
                    "h1" ->
                        HtmlParagraph { HtmlText(it, modifier, style.copy(fontSize = MaterialTheme.typography.titleLarge.fontSize), uriHandler, domain) }
                    "h2" ->
                        HtmlParagraph { HtmlText(it, modifier, style.copy(fontSize = MaterialTheme.typography.titleMedium.fontSize), uriHandler, domain) }
                    "h3" ->
                        HtmlParagraph { HtmlText(it, modifier, style.copy(fontSize = MaterialTheme.typography.titleSmall.fontSize), uriHandler, domain) }
                    "blockquote" ->
                        HtmlBlockQuote {
                            HtmlText(it, modifier, style, uriHandler, domain)
                        }
                    "br" -> HtmlLineBreak()
                    "ol" -> HtmlList(it, HtmlListType.ORDERED, modifier, style)
                    "ul" -> HtmlList(it, HtmlListType.UNORDERED, modifier, style)
                    "hr" -> Divider()
                    "p" -> HtmlParagraph {
                        HtmlText(it, modifier, style, uriHandler, domain)
                    }
                    "img" -> HtmlImage(it, domain = domain)
                    "table" -> HtmlTable(it, modifier, style, uriHandler, domain)
                    "a" -> HtmlLink(it, modifier, style, uriHandler, domain)

                    else -> HtmlText(it, modifier, style, uriHandler, domain)
                }
            }
        }
    }
}