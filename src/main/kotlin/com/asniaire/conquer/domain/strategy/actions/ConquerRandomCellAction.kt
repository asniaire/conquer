package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates
import kotlin.random.Random

class ConquerRandomCellAction() : Action {
    override fun perform(player: Player, battleBoard: BattleBoard) {
        val randomPosX = Random.nextInt(0, battleBoard.width)
        val randomPosY = Random.nextInt(0, battleBoard.height)
        val randomCoordinates = Coordinates(randomPosX, randomPosY)
        battleBoard.conquer(randomCoordinates, player)
    }
}