package org.orca.htmltext.element

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import org.orca.htmltext.util.nonEmptyChildren

/**
 * [CompositionLocal] provider for a mapping between HTML tag names and renderers for them.
 * You can override this to change how elements are rendered, or to extend the element list with custom ones!
 *
 * **Example**:
 * ```kt
 * private val HtmlExample = HtmlElementRenderer { element, modifier ->
 *     Card(modifier) {
 *         HtmlParagraphDisplay {
 *             HtmlChildRenderer(element)
 *         }
 *     }
 * }
 *
 * @Composable
 * fun App() {
 *
 *     val originalTagMap = LocalHtmlTextTagMap.current
 *
 *     val extendedTagMap = remember {
 *         originalTagMap.extend(
 *             "example" to HtmlExample
 *         )
 *     }
 *
 *     CompositionLocalProvider(LocalHtmlTextTagMap provides extendedTagMap) {
 *         HtmlText(document)
 *     }
 *
 * }
 * ```
 *
 * Please note as well that you must have Jsoup included as a dependency directly to use this functionality.
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
 * Extend the list of element renderers with a new set of tags.
 */
fun Map<String, HtmlElementRenderer>.extend(vararg pairs: Pair<String, HtmlElementRenderer>) =
    toMutableMap()
        .also { it.putAll(pairs) }
        .toMap()

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
