package com.asniaire.conquer.domain.player

import com.asniaire.conquer.domain.avatar.Avatar
import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.strategy.Strategy
import kotlinx.coroutines.CoroutineScope

data class Player(val avatar: Avatar, private val strategy: Strategy) {

    var currentCoordinates = strategy.initialCoordinates
    fun runNextMovement(gameBoard: GameBoard) {
        strategy.applyNextStrategy(this, gameBoard)
    }

}