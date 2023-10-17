package com.zaki.pintask.presentation.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zaki.pintask.R

/**
 * Created by Zaki on 13-10-2023.
 */
@Composable
fun ComposeLottieAnimation(
    modifier: Modifier,
    @RawRes res: Int = R.raw.no_tasks,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}