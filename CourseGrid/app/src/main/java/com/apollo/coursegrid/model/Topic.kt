package com.apollo.coursegrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicName: Int,
    val courseCount: Int,
    @DrawableRes val topicImage: Int
)