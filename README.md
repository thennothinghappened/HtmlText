# HtmlText
 A simple HTML renderer for Compose Multiplatform.

<img src="DemoData/Dark.png" width="40%">
<img src="DemoData/Light.png" width="40%">

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
 * <img src="https://live.staticflickr.com/6146/5991999576_7dac1e9e51_h.jpg" width="40%">: \<img src="">
