package org.orca.htmltext

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.orca.htmltext.element.HtmlChildRenderer
import org.orca.htmltext.element.HtmlElementRenderer
import org.orca.htmltext.element.HtmlParagraphDisplay
import org.orca.htmltext.element.LocalHtmlTextTagMap
import org.orca.htmltext.element.extend

private val input = """
<html>
<body>
    <h1>Page <u>Header</u>!</h1>
    <h2>Subheading</h2>
    <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    </p>
    <h2>Next subheading</h2>
    <b>Bold text</b>, and some <i>italic text</i>. A <i><b><a href="https://example.com">Bold, Italic Link</a></b></i>
    <blockquote>
        Something famous said by some person!
        <hr>
        <ul>
            <li>List item</li>
            <li>List item #2</li>
        </ul>
        <example>
            Hello!
        </example>
    </blockquote>
    <table>
        <tbody>
            <tr>
                <td>Some table item</td>
                <td>Some other table item</td>
            </tr>
            <tr>
                <td>Yet another table item</td>
                <td>
                    One more table item again
                    <ol>
                        <li>List item</li>
                        <li>List item #2</li>
                    </ol>
                </td>
            </tr>
        </tbody>
    </table>
   
</body>
</html>
    """.trimIndent()

private val HtmlExample = HtmlElementRenderer { element, modifier ->
    Card(modifier) {
        HtmlParagraphDisplay {
            HtmlChildRenderer(element)
        }
    }
}

@Composable
private fun HtmlTextPreviewContent() {

    val originalTagMap = LocalHtmlTextTagMap.current
    val extendedTagMap = remember {
        originalTagMap.extend(
            "example" to HtmlExample
        )
    }

    CompositionLocalProvider(LocalHtmlTextTagMap provides extendedTagMap) {
        Surface(Modifier.fillMaxSize()) {
            HtmlText(
                document = input,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@Preview
@Composable
private fun LightHtmlTextPreview() {
    MaterialTheme {
        HtmlTextPreviewContent()
    }
}

@Preview
@Composable
private fun DarkHtmlTextPreview() {
    MaterialTheme(darkColorScheme()) {
        HtmlTextPreviewContent()
    }
}