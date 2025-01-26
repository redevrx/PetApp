package org.redev.rx.app.features.home.domain.model

import androidx.compose.runtime.Immutable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

enum class PetType {
    Dog,
    Cat,
    Bird,
    Fish
}

@Immutable
data class PetModel @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = Uuid.random().toString(),
    val name: String, val type: PetType, var age: String, val title: String,
    val subtitle: String, val localtion: String, val image: String,
    val color:String,
)
