package org.orca.htmltext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

@Composable
fun HtmlText(
    document: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null
) {
    val html = Jsoup.parse(document)

    FlowRow(modifier) {
        HtmlText(html, style = style, uriHandler = uriHandler, domain = domain)
    }
}

@Composable
private fun HtmlText(
    node: Node,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
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
                        HtmlBlockQuote(style = style) {
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

@Composable
private fun HtmlImage(
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

private fun validateUrl(
    url: String,
    domain: String? = null
): String? {

    if (domain != null && url.startsWith("/")) return domain + url.replace(" ", "%20")
    if (!url.startsWith("http")) return null
    return url.replace(" ", "%20")
}

@Composable
private fun HtmlLink(
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

@Composable
private fun HtmlLineBreak() {
    Spacer(Modifier.fillMaxWidth())
}

@Composable
private fun HtmlHardLineBreak(style: TextStyle = TextStyle()) {
    Text("\n")
}

@Composable
private fun HtmlParagraph(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp),
        content = content
    )
}

@Composable
private fun HtmlBlockQuote(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    content: @Composable () -> Unit
) {
    Row(
        modifier
            .padding(start = 8.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(DividerDefaults.color)
        )
        HtmlParagraph(
            modifier
                .padding(start = 8.dp),
            content
        )
    }
}

private enum class HtmlListType {
    ORDERED,
    UNORDERED
}

@Composable
private fun HtmlList(
    node: Element,
    listType: HtmlListType,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null
) {

    val list: List<Node> = node.childNodes().filterNot { it.toString().trim() == "" }

    HtmlParagraph {
        Column(Modifier.padding(start = 8.dp)) {
            list.forEachIndexed { index, listItem ->
                Row(Modifier.fillMaxWidth()) {
                    when (listType) {
                        HtmlListType.ORDERED -> Text("${index+1}.", style = style)
                        HtmlListType.UNORDERED -> Text("•")
                    }
                    Text(" ")
                    FlowRow { HtmlText(listItem, modifier, style, uriHandler, domain) }
                }
            }
        }
    }
}

@Composable
private fun HtmlTable(
    node: Element,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    uriHandler: UriHandler = LocalUriHandler.current,
    domain: String? = null

) {
    val children =  node.children()
    val body = children.filter { it -> it.nodeName() == "tbody" }

    HtmlParagraph {
        Column(
            modifier
                .height(IntrinsicSize.Min)
                .border(2.dp, DividerDefaults.color)
        ) {
            // handle elements explicitly in the body
            body.forEach { body ->
                body.children().forEach { tableRow ->
                    Row(
                        Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth()
                    ) {
                        tableRow.children().forEach { tableCell ->
                            Column(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .border(1.dp, DividerDefaults.color)
                            ) {
                                HtmlText(
                                    tableCell,
                                    Modifier.padding(8.dp),
                                    style,
                                    uriHandler,
                                    domain
                                )
                            }
                        }
                    }
                }
            }
            // todo: alternatively handle elements not in the body
        }
    }
}