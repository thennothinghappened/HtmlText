package org.orca.htmltext.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.orca.htmltext.HtmlText
import org.orca.htmltext.element.LocalHtmlTextTagMap
import org.orca.htmltext.example.tags.rememberExtendedTagMap

@Composable
fun App() {

    var useDarkTheme by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = when (useDarkTheme) {
            true -> darkColorScheme()
            false -> lightColorScheme()
        }
    ) {
        Surface(Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                HtmlExampleContent()

                Column {

                    var userHtmlInput by remember { mutableStateOf("""
                        <h1>You can try writing some of your own HTML in here!</h1>

                        <b>hello!</b>
                        <span>stuff</span>

                        <p>
                            <a href="https://google.com">A link somewhere</a>
                        </p>
                    """.trimIndent()) }

                    OutlinedTextField(
                        value = userHtmlInput,
                        onValueChange = { userHtmlInput = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Card {
                        HtmlText(
                            document = userHtmlInput,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                }

                Button({ useDarkTheme = !useDarkTheme }) {
                    Text("Toggle Dark Theme", style = MaterialTheme.typography.labelMedium)
                }

            }

        }
    }

}

@Composable
fun HtmlExampleContent() {

    val extendedTagMap = rememberExtendedTagMap()

    CompositionLocalProvider(LocalHtmlTextTagMap provides extendedTagMap) {
        Column {

            HtmlText(
                document = input,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            HtmlText(
                document = inputDocument,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

