package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.strategy.Coordinates

sealed class Cell(val coordinates: Coordinates, val boost: Boost? = null)

