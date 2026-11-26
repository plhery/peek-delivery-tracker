package com.plhery.peekdeliverytracker.ui.deliveries

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.plhery.peekdeliverytracker.core.Localizer
import com.plhery.peekdeliverytracker.core.NoteSnippet
import com.plhery.peekdeliverytracker.core.SearchMatch
import com.plhery.peekdeliverytracker.core.SearchRange
import com.plhery.peekdeliverytracker.core.api.Parcel
import com.plhery.peekdeliverytracker.ui.theme.PeekTheme
import com.plhery.peekdeliverytracker.ui.theme.sfLines
import kotlin.math.abs

/**
 * What the reader's own list is searching for, empty when it isn't (the web's `ListSearch`). Only the list gives it: a
 * card anywhere else marks nothing and shows no note.
 */
internal val LocalListSearch = compositionLocalOf { "" }

/** Code point ranges as offsets in `text`, which is how Compose cuts it. */
private fun offsets(text: String, ranges: List<SearchRange>): List<IntRange> =
    ranges.map { text.offsetByCodePoints(0, it.start) until text.offsetByCodePoints(0, it.end) }

/** Where the list's search stands in `text`, while the reader's own list is searching. */
@Composable
internal fun listSearchMarks(text: String): List<IntRange> {
    val query = LocalListSearch.current
    return remember(text, query) { offsets(text, SearchMatch.ranges(text, query)) }
}

/**
 * The few words of the note the list's search found the parcel by (the web's `useNoteMatch`): only while searching,
 * and only when the note or a file's name holds the search.
 */
@Composable
internal fun listNoteMatch(parcel: Parcel): NoteSnippet? {
    val query = LocalListSearch.current
    return remember(parcel.note, parcel.files, query) {
        if (query.isBlank()) null else SearchMatch.noteSnippet(parcel.note, parcel.files.orEmpty().map { it.name }, query)
    }
}

/** What TalkBack hears after a card's name: "Your note: " and the words a search found, or that it has a note. */
internal fun noteHeard(parcel: Parcel, snippet: NoteSnippet?, localizer: Localizer): String? =
    snippet?.let { "${localizer.text("note.title")}: ${it.text}" } ?: noteMarkLabel(parcel, localizer)

/**
 * Text with Peek's yellow under the search's `marks` (the web's `.search-mark`): 0.18 em thick, 0.12 em under the
 * baseline, the letters in their own ink.
 */
@Composable
internal fun SearchMarkedText(
    text: AnnotatedString,
    marks: List<IntRange>,
    modifier: Modifier,
    color: Color,
    style: TextStyle,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = emptyMap(),
) {
    val yellow = PeekTheme.colors.accent
    var layout by remember { mutableStateOf<TextLayoutResult?>(null) }
    Text(
        text,
        if (marks.isEmpty()) {
            modifier
        } else {
            modifier.drawBehind {
                val result = layout?.takeIf { it.layoutInput.text == text } ?: return@drawBehind
                val size = style.fontSize.toPx()
                for (mark in marks) {
                    val end = mark.last + 1
                    for (line in result.getLineForOffset(mark.first)..result.getLineForOffset(mark.last)) {
                        val from = maxOf(mark.first, result.getLineStart(line))
                        val to = minOf(end, result.getLineEnd(line, visibleEnd = true))
                        if (to <= from) continue
                        val left = result.getHorizontalPosition(from, usePrimaryDirection = true)
                        val right = result.getHorizontalPosition(to, usePrimaryDirection = true)
                        val top = result.getLineBaseline(line) + size * 0.12f
                        drawRect(yellow, Offset(minOf(left, right), top), Size(abs(right - left), size * 0.18f))
                    }
                }
            }
        },
        color = color,
        style = style,
        maxLines = maxLines,
        overflow = if (maxLines == Int.MAX_VALUE) TextOverflow.Clip else TextOverflow.Ellipsis,
        inlineContent = inlineContent,
        onTextLayout = { layout = it },
    )
}

/** The note's words under a card's name: one quiet line in footnote, in the secondary ink, the search's matches marked. */
@Composable
internal fun NoteMatchLine(snippet: NoteSnippet, modifier: Modifier = Modifier) {
    val text = snippet.text
    SearchMarkedText(
        AnnotatedString(text), remember(snippet) { offsets(text, snippet.ranges) }, modifier,
        color = PeekTheme.colors.secondaryText, style = PeekTheme.type.footnote.sfLines(), maxLines = 1,
    )
}
