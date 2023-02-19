# HtmlText
 A simple HTML renderer for Compose Multiplatform.

![Dark Example](DemoData/Dark.png)
![Light Example](DemoData/Light.png)

### Usage
```kotlin
HtmlText("<b>Your <u>HTML</u> here!</b>")
```
Optionally, specify a `domain` including http(s) prefix
so URLs pointing to `/<dir>` are picked up.

### Supported Tags
 * ### Headings: \<h1..3>
 * **Bold**: \<b>
 * *Italic*: \<i> / \<em>
 * <u>Underline</u>: \<u>
 * ~~Strikethrough~~: \<strike>
 * [Links](): \<a href="">
 * 1. Ordered Lists: \<ol>
   2. UnorderedLists: \<ul>
 * > Blockquotes: \<blockquote>
 * | Tables | Implementation |
   |--------|----------------------------------|
   | (WIP)    | `<table><tbody></tbody></table>` |
 * ![](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flive.staticflickr.com%2F6146%2F5991999576_5b933da0db_b.jpg&f=1&nofb=1): \<img src="">