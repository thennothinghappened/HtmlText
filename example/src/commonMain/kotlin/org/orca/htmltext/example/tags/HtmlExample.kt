package org.orca.htmltext.example.tags

import androidx.compose.material3.Card
import org.orca.htmltext.element.HtmlChildRenderer
import org.orca.htmltext.element.HtmlElementRenderer
import org.orca.htmltext.element.HtmlParagraphDisplay

internal val HtmlExample = HtmlElementRenderer { element, modifier ->
    Card(modifier) {
        HtmlParagraphDisplay {
            HtmlChildRenderer(element)
        }
    }
}
