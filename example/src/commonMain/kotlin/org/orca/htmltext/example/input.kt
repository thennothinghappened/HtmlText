package org.orca.htmltext.example

import org.jsoup.nodes.Document

internal val input = """
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
            <li><b>And a super super super long long long <u>list</u></b> item which takes up a heap of space and <i>hopefully</i> causes line breaks!</li>
            <li>List item #3</li>
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
                        <li>List <b>item</b> #2</li>
                    </ol>
                </td>
            </tr>
        </tbody>
    </table>
   
</body>
</html>
    """.trimIndent()

internal val inputDocument = Document("").apply {
    appendElement("body").apply {

        appendElement("h1").text("Hello!")

        appendElement("p").apply {

            appendText("This is some example text for showcasing ")

            appendElement("a")
                .text("HtmlText")
                .attr("href", "https://github.com/thennothinghappened/HtmlText/")

            appendText("'s support for inputting pre-made jsoup documents.")

        }

    }
}
