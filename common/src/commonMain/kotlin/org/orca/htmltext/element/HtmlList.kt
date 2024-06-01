package org.orca.htmltext.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.FlowRow
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
    Text(text = "${index+1}.")
}

@Composable
private fun HtmlUnorderedListDecorator() {

    val size = 4.dp

    Box(
        Modifier
            .padding(top = LocalTextStyle.current
                .lineHeight
                .value
                .dp / 2 - size / 2
            )
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onSurface)
    )

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
                Row {
                    when (listType) {
                        HtmlListType.ORDERED -> HtmlOrderedListDecorator(index)
                        HtmlListType.UNORDERED -> HtmlUnorderedListDecorator()
                    }

                    Spacer(Modifier.size(4.dp))

                    FlowRow(Modifier.height(IntrinsicSize.Min)) {
                        HtmlChildRenderer(listItem)
                    }
                }
            }
        }
    }
}
