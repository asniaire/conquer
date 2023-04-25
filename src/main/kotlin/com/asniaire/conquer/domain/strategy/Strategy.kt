package com.asniaire.conquer.domain.strategy

import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.player.Player

data class Coordinates(val x: Int, val y: Int)

class Strategy constructor(
    val initialCoordinates: Coordinates,
    strategySteps: List<StrategyStep>,
    private val defaultAction: Action
) {

    private val strategies: List<StrategyStep>

    init {
        strategies = strategySteps.sortedBy { it.priority }
        validate()
    }

    private fun validate() {
        checkPrioritiesOrder()
    }

    private fun checkPrioritiesOrder() {
        strategies.forEachIndexed { index, strategyStep ->
            if (index + 1 != strategyStep.priority) {
                throw Exception() // TODO
            }
        }
    }

    fun applyNextStrategy(player: Player, battleBoard: BattleBoard) =
        strategies.firstOrNull { it.conditions.all { cond -> cond.isFulfilled(battleBoard) } }
            .let {
                it?.action?.perform(player, battleBoard) ?: defaultAction.perform(player, battleBoard)
            }

}

data class StrategyStep(val conditions: List<Condition>, val action: Action, val priority: Int)