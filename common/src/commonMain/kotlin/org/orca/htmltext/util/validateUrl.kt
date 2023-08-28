package org.orca.htmltext.util

internal fun validateUrl(
    url: String,
    domain: String? = null
): String? {

    if (domain != null && url.startsWith("/")) return domain + url.replace(" ", "%20")
    if (!url.startsWith("http")) return null
    return url.replace(" ", "%20")
}