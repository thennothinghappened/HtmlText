package org.orca.htmltext.util

import org.jsoup.nodes.Node

internal fun Node.nonEmptyChildren() = childNodes()
    .filter { it.toString().isNotBlank() }
