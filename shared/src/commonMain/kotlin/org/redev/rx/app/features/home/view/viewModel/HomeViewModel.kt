package org.redev.rx.app.features.home.domain.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.redev.rx.app.features.home.domain.model.CategoryModel
import org.redev.rx.app.features.home.domain.model.PetModel
import org.redev.rx.app.features.home.domain.model.PetType
import org.redev.rx.app.utils.asFlowC


class HomeViewModel : ViewModel() {
    private val _category = MutableStateFlow<List<CategoryModel>>(listOf())
    val categories
        get() = _category.asStateFlow().asFlowC()


    fun fetchCategories() = viewModelScope.launch {
        val items = listOf(
            CategoryModel(
                name = "Dogs",
                image = "https://cdn-icons-png.flaticon.com/128/2171/2171990.png",
                color = "0xFF98D8EF"
            ),
            CategoryModel(
                name = "Cat",
                image = "https://cdn-icons-png.flaticon.com/128/6988/6988878.png",
                color = "0xFFFFA09B"
            ),
            CategoryModel(
                name = "Birds",
                image = "https://cdn-icons-png.flaticon.com/128/3338/3338192.png",
                color = "0xFFFFC785"
            ),
            CategoryModel(
                name = "Fishes",
                image = "https://cdn-icons-png.flaticon.com/128/3025/3025840.png",
                color = "0xFFAEEA94"
            ),
        );

        _category.value = items
    }


    private val _pets = MutableStateFlow<List<PetModel>>(listOf())
    val pets = _pets.asStateFlow().asFlowC()

    fun fetchDetail() = viewModelScope.launch {
        val items = listOf(
            PetModel(
                color = "0xFFFFA09B",
                name = "Snake Cat",
                type = PetType.Cat,
                age = "1 Year old",
                title = "Snake",
                subtitle = "Snake",
                localtion = "Distance with in 1 Km",
                image = "https://png.pngtree.com/png-clipart/20230511/ourmid/pngtree-isolated-cat-on-white-background-png-image_7094927.png"
            ),
            PetModel(
                color = "0xFF98D8EF",
                name = "Snake Dog",
                type = PetType.Dog,
                age = "1 Year old",
                title = "Snake",
                subtitle = "Snake",
                localtion = "Distance with in 1 Km",
                image = "https://png.pngtree.com/png-vector/20240529/ourmid/pngtree-happy-dog-giving-high-five-png-image_12445432.png"
            ),
            PetModel(
                color = "0xFFFFA09B",
                name = "Snake Dog",
                type = PetType.Cat,
                age = "1 Year old",
                title = "Snake",
                subtitle = "Snake",
                localtion = "Distance with in 1 Km",
                image = "https://www.freeiconspng.com/uploads/baby-cat-png-12.png"
            ),
            PetModel(
                color = "0xFFFFC785",
                name = "Snake Dog",
                type = PetType.Bird,
                age = "1 Year old",
                title = "Snake",
                subtitle = "Snake",
                localtion = "Distance with in 1 Km",
                image = "https://www.freeiconspng.com/uploads/png-bird-11.png"
            ),
            PetModel(
                color = "0xFFAEEA94",
                name = "Snake Dog",
                type = PetType.Fish,
                age = "1 Year old",
                title = "Snake",
                subtitle = "Snake",
                localtion = "Distance with in 1 Km",
                image = "https://img.pikbest.com/png-images/20241028/tropical-fish_11020023.png!sw800"
            ),
        );

        _pets.value = items
    }
}
