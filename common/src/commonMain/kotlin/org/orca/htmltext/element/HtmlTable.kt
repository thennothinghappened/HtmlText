package org.orca.htmltext.element

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Element
import org.orca.htmltext.HtmlText

/**
 * A HTML `table` element, which renders a grid of cells with an optional header row.
 */
val HtmlTable = HtmlElementRenderer { element, modifier ->

    val children =  element.children()
    val body = children.filter { it -> it.nodeName() == "tbody" }

    HtmlParagraphDisplay {
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
                                HtmlChildRenderer(
                                    tableCell,
                                    Modifier.padding(8.dp)
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
