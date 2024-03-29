# HorizontalChipView

A custom ChipGroup showing a title and chips in horizontal orientation by being able to specify a
name for each chip.

## HorizontalChipView in XML layout

We can use `HorizontalChipView` without any customized attributes. This `HorizontalChipView` will be
initialized with the default parameters and **without title**.

```xml
<com.indisparte.horizontalchipview.HorizontalChipView 
    android:id="@+id/chipGroup"
    android:layout_width="match_parent" 
    android:layout_height="wrap_content" />
```   

## Attribute descriptions

We can customize the view using the below attributes.

```xml
 <com.indisparte.horizontalchipview.HorizontalChipView
    android:id="@+id/chipGroup"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:title="Title" //title of section
    app:titleColor="@color/white" //title color, default white
    app:titleSize="18sp" //title size, default 14sp
    app:requireSelection="true" // Default false
    app:selectedChipId="@id/my_chip // Default null
    app:singleCheck="false"//Default false
    app:titleStyle="bold"  //title style, default bold
    app:titleTypeface="@font/my_font" /> // title font
```   
## Add a custom layout to the chips
If you want to change the style of the chips within the view, indicate the following attribute in the `app:chipStyle` view

```xml
 <com.indisparte.horizontalchipview.HorizontalChipView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:chipStyle="@style/CustomFilterChipStyle" // Default null, add custom style to chips
    //other...
    />
``` 
After that, create in the file `res/themes.xml` your custom chip theme :

```xml
 <style name="CustomFilterChipStyle" parent="Widget.MaterialComponents.Chip.Filter">
    <item name="chipBackgroundColor">@color/background_color_chip_state_list</item>
    <item name="chipStrokeColor">@color/royal_blue_100</item>
    <item name="chipStrokeWidth">2dp</item>
    <item name="chipCornerRadius">10dp</item>
    <item name="android:textColor">@color/alabaster</item>
</style>
``` 

## Add chips into `HorizontalChipView`

To add chips inside our view we need to use the following method:

```kotlin
//other code...
val genres: List<Genre>

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //bind the custom view
    val chipGroup: HorizontalChipView<Genre> =
        view.findViewById<HorizontalChipView<Genre>>(R.id.chiGroup)

    //set all the elements
    chipGroup.setChipsList(
        genres,//The list of items to be included in the chip group
        textGetter = { genre -> genre.name }//The text to be included in the chip
    )
    
    //optional : specify the action on chips click
    chipGroup.onChipClicked = { genre ->
        Toast.makeText(
            requireContext(),
            "${genre.name} genre",
            Toast.LENGTH_SHORT
        ).show()
    }
    
    //optional: set chip attributes
    chipGroup.setChipAttributes { chip ->
        // set any specific attributes for the chip here
    }
    
    //other code
}
```

## Screenshots

![screenshot](screenshot/image.png)

