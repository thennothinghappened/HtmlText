package org.orca.htmltext.example.tags

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.orca.htmltext.element.LocalHtmlTextTagMap
import org.orca.htmltext.element.extend

@Composable
fun rememberExtendedTagMap() = LocalHtmlTextTagMap.current
    .let {
        remember {
            it.extend(
                "example" to HtmlExample
            )
        }
    }
