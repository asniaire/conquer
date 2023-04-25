package com.asniaire.conquer.domain

import com.asniaire.conquer.domain.avatar.Avatar
import com.asniaire.conquer.domain.avatar.AvatarId
import com.asniaire.conquer.domain.avatar.AvatarName
import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.board.BoardCell
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.*
import com.asniaire.conquer.domain.strategy.actions.ConquerCellAction
import com.asniaire.conquer.domain.strategy.actions.ConquerRandomCellAction
import com.asniaire.conquer.domain.strategy.actions.Direction
import com.asniaire.conquer.domain.strategy.actions.LinearMovementAction
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.util.*

class BattleBoardTest {

    @Test
    fun `success battle board`() {
        val initialCoordinates = Coordinates(1, 1)
        val condition = RemainingCellsCondition(Comparison.GREATER_THAN, 3)
        val action = LinearMovementAction(Direction.EAST)
        val defaultAction = ConquerRandomCellAction()
        val strategyStep1 = StrategyStep(listOf(condition), action, 1)
        val strategy = Strategy(initialCoordinates, listOf(strategyStep1), defaultAction)

        val player1 = Player(Avatar(AvatarId.fromString(UUID.randomUUID().toString()), AvatarName("Zoby")), strategy)
        val player2 = Player(Avatar(AvatarId.fromString(UUID.randomUUID().toString()), AvatarName("Tom")), strategy)

        val cell11 = BoardCell(Coordinates(1, 1))
        val cell12 = BoardCell(Coordinates(1, 2))
        val cell13 = BoardCell(Coordinates(1, 3))
        val row1 = arrayOf<BoardCell>(cell11, cell12, cell13)
        val cell21 = BoardCell(Coordinates(2, 1))
        val cell22 = BoardCell(Coordinates(2, 2))
        val cell23 = BoardCell(Coordinates(2, 3))
        val row2 = arrayOf<BoardCell>(cell21, cell22, cell23)
        val board = arrayOf<Array<BoardCell>>(row1, row2)

        val battleBoard = BattleBoard("Test", board, listOf(player1, player2))
        runBlocking {
            battleBoard.simulate()
        }
    }

}