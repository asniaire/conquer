package com.asniaire.conquer.domain.player

import com.asniaire.conquer.domain.avatar.Avatar
import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.strategy.Strategy

data class Player(val avatar: Avatar, private val strategy: Strategy) {

    var currentCoordinates = strategy.initialCoordinates
    fun runNextMovement(battleBoard: BattleBoard) {
        strategy.applyNextStrategy(this, battleBoard)
    }

}