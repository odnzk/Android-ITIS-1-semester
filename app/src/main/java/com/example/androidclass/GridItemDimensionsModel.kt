package com.example.androidclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GridItemDimensionsModel(
    val horizontalItemWidth: Int,
    val horizontalItemHeight: Int,
    val verticalRectangleItemWidth: Int,
    val verticalRectangleItemHeight: Int,
    val verticalSquareItemWidth: Int,
    val verticalSquareItemHeight: Int,
    val halfMargin: Int
): Parcelable
