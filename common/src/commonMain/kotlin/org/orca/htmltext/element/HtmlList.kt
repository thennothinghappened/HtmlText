package org.orca.htmltext.element

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jsoup.nodes.Element
import org.orca.htmltext.util.nonEmptyChildren

/**
 * An unordered bullet-point list of items.
 */
val HtmlTextUnorderedList = HtmlElementRenderer { element, modifier ->
    HtmlList(element, HtmlListType.UNORDERED, modifier)
}

/**
 * A numbered list of items.
 */
val HtmlTextOrderedList = HtmlElementRenderer { element, modifier ->
    HtmlList(element, HtmlListType.ORDERED, modifier)
}

private enum class HtmlListType {
    ORDERED,
    UNORDERED
}

@Composable
private fun HtmlOrderedListDecorator(index: Int) {
    Text("${index + 1}.")
}

@Composable
private fun HtmlUnorderedListDecorator() {
    Text("•")
}

/**
 * HTML ordered or unordered list (`ol`, `ul`)
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun HtmlList(
    node: Element,
    listType: HtmlListType,
    modifier: Modifier = Modifier
) {
    HtmlParagraphDisplay(modifier) {
        Column(Modifier.padding(start = 8.dp)) {
            node.nonEmptyChildren().forEachIndexed { index, listItem ->
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {

                    when (listType) {
                        HtmlListType.ORDERED -> HtmlOrderedListDecorator(index)
                        HtmlListType.UNORDERED -> HtmlUnorderedListDecorator()
                    }

                    FlowRow {
                        HtmlChildRenderer(listItem)
                    }

                }
            }
        }
    }
}
