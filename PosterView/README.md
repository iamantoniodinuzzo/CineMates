# PosterView

A custom PosterView that allows a media poster to be displayed indicating the title and a rating, if
any. Title and rating can be null, the view in this case will hide them

## PosterView in XML layout

We can use `PosterView` with only image url will be shown only image. (This example use _data
binding_)

```xml
<com.indisparte.posterview.PosterView 
    android:layout_width="100dp" 
    android:layout_height="150dp"
    app:imageSrc="@{imageUrl}" />
```    

## PosterView attributes:

We can customize the view using the below attributes.

```xml

<com.indisparte.posterview.PosterView 
    android:layout_width="100dp" 
    android:layout_height="150dp"
    app:imageSrc="@{imageUrl}" 
    app:placeholder="@drawable/placeholder"
    app:posterRadius="8dp"//default 10dp
    app:title="Image title"// if it is empty,the title will be hidden
    app:titleColor="@color/black" //default white
    app:titleSize="18sp"//default 18sp
    app:titlePosterStyle="bold" // default bold
    app:ratingColor="@color/white"//default white
    app:ratingSize="14sp"//default 14sp
    app:ratingStyle="normal"//default normal
    app:ratingRadius="8dp"//default 8dp
    app:rating="8.7"//if it is empty,the rating will be hidden />
```  

## Screenshots

![screenshot_image_title_vote](screenshot/poster_title_vote.png)
![screenshot_only_image](screenshot/poster.png)


