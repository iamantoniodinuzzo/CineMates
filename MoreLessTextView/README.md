# MoreLessTextView

A custom TextView that allows you to truncate text that exceeds a specified number of lines, displaying customizable clickable text that allows you to expand the entire text.

## MoreLessTextView in XML layout

We can use `MoreLessTextView` without any customized attributes. This `MoreLessTextView` will be
initialized with the default parameters and show all text

```xml
 <com.indisparte.morelesstextview.MoreLessTextView
    android:id="@+id/readMoreTextV"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textColor="@color/alabaster"
    android:textSize="14sp"
    android:text="Lorem ipsum"/>
```    

## Attributes description:

```xml
 <com.indisparte.morelesstextview.MoreLessTextView
    android:id="@+id/readMoreTextV"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textColor="@color/alabaster"
    android:textSize="14sp"
    app:clickableColor="@color/blue" //The color of the text to expand the block
    app:maxLinesShown="2" // The maximum number of lines of text to be shown
    android:text="Lorem ipsum and very very long text"
    app:readMoreText="Read more" />
``` 

## Screenshots

![screenshot](screenshot/image.png)

