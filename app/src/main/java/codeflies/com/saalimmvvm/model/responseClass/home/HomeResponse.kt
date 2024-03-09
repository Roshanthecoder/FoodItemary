package codeflies.com.saalimmvvm.model.responseClass.home

import java.io.Serializable

data class HomeResponse(
    val sliderArray: List<SliderArray>,
    val groceryItemsArray: List<HomeItem>,
    val topBrandArray: List<HomeItem>,
    val topCategoryArray: List<HomeItem>
):Serializable

data class HomeItem(
    val id: String,  // This can be used to identify the type of item (e.g., topBrand, topCategory, etc.)
    val name: String,
    val image: String
): Serializable