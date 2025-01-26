package org.redev.rx.app.features.home.domain.model

import androidx.annotation.ColorLong
import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


@Immutable
data class CategoryModel @OptIn(ExperimentalUuidApi::class)
constructor(
    val id: String = Uuid.random().toString(),
    val name: String, val image: String, val color: String
)
