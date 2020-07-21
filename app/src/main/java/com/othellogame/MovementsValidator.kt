package com.othellogame

class MovementsValidator(var board: Board) {

    /*fun isTakenPosition(movement: Movement): Boolean {
        return board.getValueInPosition(movement.row, movement.column) != board.emptyIdentifier
    }

    fun isEmptyPosition(movement: Movement): Boolean {
        return board.getValueInPosition(movement.row, movement.column) == board.emptyIdentifier
    }*/

    fun getSidesForValidate(movement: Movement) {


    }

    fun isValidFieldForMovement(movement: Movement): Boolean {
        return board.isEmptyPosition(movement) && board.getValueInPosition(
            movement.row,
            movement.column
        ) != movement.disk.color
    }

    fun hasToValidateLeftSideInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInLeftBorder(movement.row) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateRightSideInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInRightBorder(movement.row) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateTopSideInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInTopBorder(movement.row) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateBottomSideInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInBottomBorder(movement.row) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateUpperLeftDiagonalInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInUpperLeftCorner(movement.row, movement.column) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateLowerLeftDiagonalInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInLowerLeftCorner(movement.row, movement.column) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateUpperRightDiagonalInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInUpperRightCorner(movement.row, movement.column) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }

    fun hasToValidateLowerRightDiagonalInMovement(movement: Movement): Boolean {
        return when {
            board.isPositionInLowerRightCorner(movement.row, movement.column) -> false
            isValidFieldForMovement(movement) -> true
            else -> true
        }
    }
}
