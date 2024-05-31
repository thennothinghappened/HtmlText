package org.orca.htmltext.element

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import org.orca.htmltext.HtmlText

/**
 * HTML element renderer for a Heading 1.
 */
val HtmlTextH1 = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(MaterialTheme.typography.titleLarge) {
        HtmlParagraphDisplay(modifier) {
            HtmlChildRenderer(element)
        }
    }
}

/**
 * HTML element renderer for a Heading 2.
 */
val HtmlTextH2 = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(MaterialTheme.typography.titleMedium) {
        HtmlParagraphDisplay(modifier) {
            HtmlChildRenderer(element)
        }
    }
}

/**
 * HTML element renderer for a Heading 3.
 */
val HtmlTextH3 = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(MaterialTheme.typography.titleSmall) {
        HtmlParagraphDisplay(modifier) {
            HtmlChildRenderer(element)
        }
    }
}

/**
 * A horizontal line spanning the page width.
 */
val HtmlTextHorizontalRule = HtmlElementRenderer { _, modifier ->
    HorizontalDivider(modifier)
}

/**
 * HTML element renderer that uses bold text.
 */
val HtmlTextBold = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(TextStyle(fontWeight = FontWeight.Bold)) {
        HtmlChildRenderer(element, modifier)
    }
}

/**
 * HTML element renderer that uses italic text.
 */
val HtmlTextItalic = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(TextStyle(fontStyle = FontStyle.Italic)) {
        HtmlChildRenderer(element, modifier)
    }
}

/**
 * HTML element renderer that underlines text.
 */
val HtmlTextUnderline = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(TextStyle(textDecoration = TextDecoration.Underline)) {
        HtmlChildRenderer(element, modifier)
    }
}

/**
 * HTML element renderer that adds a strike through the middle of text.
 */
val HtmlTextStrike = HtmlElementRenderer { element, modifier ->
    ProvideTextStyle(TextStyle(textDecoration = TextDecoration.LineThrough)) {
        HtmlChildRenderer(element, modifier)
    }
}

