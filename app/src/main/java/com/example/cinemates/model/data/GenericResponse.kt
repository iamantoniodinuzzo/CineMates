package com.example.cinemates.model.data

import java.util.ArrayList

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:39
 */
class GenericResponse<T>(
    var page: Int,
    var total_pages: Int,
    var total_results: Int,
    var results: List<T>
)