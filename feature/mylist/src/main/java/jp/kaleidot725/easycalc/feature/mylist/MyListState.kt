package jp.kaleidot725.easycalc.feature.mylist

import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts

data class MyListState(
    val mathTexts: MathTexts = MathTexts.EMPTY,
)
