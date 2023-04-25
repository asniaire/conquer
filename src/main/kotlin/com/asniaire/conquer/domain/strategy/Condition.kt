package com.asniaire.conquer.domain.strategy

import com.asniaire.conquer.domain.avatar.Avatar
import com.asniaire.conquer.domain.avatar.AvatarId
import com.asniaire.conquer.domain.avatar.AvatarName
import com.asniaire.conquer.domain.board.Boost
import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.board.GameCell
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.actions.ConquerCellAction

enum class Comparison {
    LESS_THAN {
        override fun operation(leftValue: Int, rightValue: Int) = leftValue < rightValue
    },
    LESS_OR_EQUAL_THAN {
        override fun operation(leftValue: Int, rightValue: Int) = leftValue <= rightValue
    },
    GREATER_THAN {
        override fun operation(leftValue: Int, rightValue: Int) = leftValue > rightValue
    },
    GREATER_OR_EQUAL_THAN {
        override fun operation(leftValue: Int, rightValue: Int) = leftValue >= rightValue
    },
    EQUAL {
        override fun operation(leftValue: Int, rightValue: Int) = leftValue == rightValue
    };

    abstract fun operation(leftValue: Int, rightValue: Int): Boolean

    companion object {
        fun getByName(name: String) = valueOf(name.uppercase())
    }
}

sealed class Condition(val comparison: Comparison, val value: Int) {
    abstract fun isFulfilled(gameBoard: GameBoard): Boolean
}

class RemainingCellsCondition(comparison: Comparison, value: Int) : Condition(comparison, value) {
    override fun isFulfilled(gameBoard: GameBoard) =
        comparison.operation(value, gameBoard.remainingCells)
}

class ConqueredCellsCondition(
    value: Int,
    comparison: Comparison,
    private val player: Player
) : Condition(comparison, value) {
    override fun isFulfilled(gameBoard: GameBoard) =
        comparison.operation(value, gameBoard.conqueredCellsByUser(player))
}



// TODO Remove this code
fun test() {
    val condition = RemainingCellsCondition(Comparison.GREATER_OR_EQUAL_THAN, 19)
    val action = ConquerCellAction(Coordinates(1, 1))
    val strategyStep = StrategyStep(listOf(condition), action, 1)

    val cell11 = GameCell(Coordinates(1, 1), Boost())
    val cells = arrayOf(arrayOf(cell11))
    val strategy = Strategy(Coordinates(1, 1), listOf(strategyStep), action)
    val avatar1 = Avatar(AvatarId.fromString("avatar-1"), AvatarName("Ossy"))
    val avatar2 = Avatar(AvatarId.fromString("avatar-2"), AvatarName("Ken"))
    val player1 = Player(avatar1, strategy)
    val player2 = Player(avatar2, strategy)

    val gameBoard = GameBoard("Test", cells, listOf(player1, player2))
}
