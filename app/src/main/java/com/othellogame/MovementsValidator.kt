package com.othellogame

class MovementsValidator(var board: Board){

    fun isTakenPosition(movement: Movement): Boolean {
        return board.getValueInPosition(movement.row, movement.column) != board.emptyIdentifier
    }
}

