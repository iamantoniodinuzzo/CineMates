package com.example.cinemates.domain.model


data class Group(
    val episodeDTOS: List<Episode>,
    val id: String,
    val locked: Boolean,
    val name: String,
    val order: Int
)