package com.example.cinemates.network.response

/**
 * @author Antonio Di Nuzzo
 * Created 19/06/2022 at 10:05
 */
class ImagesResponse(
    var id: Int,
    var backdrops: List<Image>,
    var posters: List<Image>
) {
    /**
     * @author Antonio Di Nuzzo
     * Created 19/06/2022 at 11:00
     */
    class Image(
        var file_path: String,
        var height: Int,
        var width: Int
    ) {
        val imageType: Int
            get() = if (height > width) POSTER else BACKDROP

        companion object {
            const val BACKDROP = 0
            const val POSTER = 1
        }
    }
}