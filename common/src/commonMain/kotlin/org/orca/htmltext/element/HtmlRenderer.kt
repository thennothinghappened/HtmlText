package org.orca.htmltext.element

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import org.orca.htmltext.HtmlText
import org.orca.htmltext.util.nonEmptyChildren

/**
 * [CompositionLocal] provider for a mapping between HTML tag names and renderers for them.
 */
val LocalHtmlTextTagMap = staticCompositionLocalOf {
    mapOf(
        "strong" to HtmlTextBold,
        "b" to HtmlTextBold,
        "i" to HtmlTextItalic,
        "em" to HtmlTextItalic,
        "u" to HtmlTextUnderline,
        "strike" to HtmlTextStrike,
        "h1" to HtmlTextH1,
        "h2" to HtmlTextH2,
        "h3" to HtmlTextH3,
        "blockquote" to HtmlTextBlockQuote,
        "br" to HtmlLineBreak,
        "a" to HtmlTextAnchor,
        "ol" to HtmlTextOrderedList,
        "ul" to HtmlTextUnorderedList,
        "hr" to HtmlTextHorizontalRule,
        "p" to HtmlParagraph,
        "img" to HtmlImage,
        "table" to HtmlTable
    )
}

/**
 * The main renderer for elements. The renderer recurses down the tree of elements and matches element tags against
 * the provided tag map from [LocalHtmlTextTagMap], and renders [TextNode] leafs as [Text].
 */
@Suppress("ReplaceGetOrSet", "SimpleRedundantLet")
@Composable
fun HtmlChildRenderer(node: Node, modifier: Modifier = Modifier) {
    LocalHtmlTextTagMap.current.let { htmlTagMap ->
        node.nonEmptyChildren().forEach { child ->
            when (child) {
                is TextNode -> { Text(text = child.text(), modifier = modifier) }
                is Element -> {
                    htmlTagMap.get(child.nodeName())
                        ?.let { it.Render(child, modifier) }
                        ?: HtmlChildRenderer(child, modifier)
                }
            }
        }
    }
}