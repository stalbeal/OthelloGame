package com.othellogame

sealed class MovementsSide(val board: Board, val movement: Movement) {

    abstract fun getDisksToChangeInSide(): List<Disk>

    abstract fun hasToValidateSideInMovement(): Boolean

    protected fun isEmptyPosition(filledDiskColor: String) = filledDiskColor == "0"

    protected fun isValidFieldForMovement(movement: Movement): Boolean {
        return board.isEmptyPosition(movement) &&
                board.getValueInPosition(movement.row, movement.column) != movement.disk.color
    }

    fun getSidesWithDisksToChange(board: Board, movement: Movement): ArrayList<List<Disk>> {

        return ArrayList<List<Disk>>().apply {
            when {
                Left(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(Left(board, movement).getDisksToChangeInSide())

                Right(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(Right(board, movement).getDisksToChangeInSide())

                Top(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(Top(board, movement).getDisksToChangeInSide())

                Bottom(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(Bottom(board, movement).getDisksToChangeInSide())

                UpperLeftDiagonal(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(UpperLeftDiagonal(board, movement).getDisksToChangeInSide())

                UpperRightDiagonal(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(UpperRightDiagonal(board, movement).getDisksToChangeInSide())

                LowerLeftDiagonal(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(LowerLeftDiagonal(board, movement).getDisksToChangeInSide())

                LowerRightDiagonal(board, movement).getDisksToChangeInSide().isNotEmpty() ->
                    add(LowerRightDiagonal(board, movement).getDisksToChangeInSide())

            }
        }
    }

    class Left(board: Board, movement: Movement) : MovementsSide(board, movement) {

        override fun hasToValidateSideInMovement(): Boolean {
            if (board.isPositionInLeftBorder(movement.row)) {
                return false
            }

            return isValidFieldForMovement(movement)
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var column = movement.column - 1
            var filledDiskColor = board.getValueInPosition(movement.row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor && column >= 0) {
                diskList.add(board.getDiskInPosition(movement.row, column))
                column--
                if (column >= 0) {
                    filledDiskColor = board.getValueInPosition(movement.row, column)
                }
            }

            if (column < 0 || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }

    }

    class Right(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            if (board.isPositionInRightBorder(movement.column)) {
                return false
            }

            return isValidFieldForMovement(movement)
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var column = movement.column + 1
            var filledDiskColor = board.getValueInPosition(movement.row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(movement.row, column))
                column++
                if (column < board.size) {
                    filledDiskColor = board.getValueInPosition(movement.row, column)
                }
            }

            if (column > board.size || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class Top(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInTopBorder(movement.row) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row - 1
            var filledDiskColor = board.getValueInPosition(row, movement.column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, movement.column))
                row--
                if (row >= 0) {
                    filledDiskColor = board.getValueInPosition(row, movement.column)
                }
            }

            if (row < 0 || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class Bottom(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInBottomBorder(movement.row) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row + 1
            var filledDiskColor = board.getValueInPosition(row, movement.column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, movement.column))
                row++
                if (row < board.size) {
                    filledDiskColor = board.getValueInPosition(row, movement.column)
                }
            }

            if (row < 0 || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class LowerRightDiagonal(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInLowerRightCorner(movement.row, movement.column) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row + 1
            var column = movement.column + 1
            var filledDiskColor = board.getValueInPosition(row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, column))
                row++
                column++
                if (row < board.size && column < board.size) {
                    filledDiskColor = board.getValueInPosition(row, column)
                }
            }

            if ((row >= board.size || column >= board.size) || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class UpperRightDiagonal(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInUpperRightCorner(movement.row, movement.column) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row - 1
            var column = movement.column + 1
            var filledDiskColor = board.getValueInPosition(row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, column))
                row--
                column++
                if (row >= 0 && column < board.size) {
                    filledDiskColor = board.getValueInPosition(row, column)
                }
            }

            if ((row < 0 || column >= board.size) || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class LowerLeftDiagonal(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInLowerLeftCorner(movement.row, movement.column) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row + 1
            var column = movement.column - 1
            var filledDiskColor = board.getValueInPosition(row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, column))
                row++
                column--
                if (row < board.size && column >= 0) {
                    filledDiskColor = board.getValueInPosition(row, column)
                }
            }

            if ((row >= board.size || column < 0) || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }

    class UpperLeftDiagonal(board: Board, movement: Movement) : MovementsSide(board, movement) {
        override fun hasToValidateSideInMovement(): Boolean {
            return when {
                board.isPositionInUpperLeftCorner(movement.row, movement.column) -> false
                isValidFieldForMovement(movement) -> true
                else -> true
            }
        }

        override fun getDisksToChangeInSide(): List<Disk> {
            if (!hasToValidateSideInMovement()) {
                return listOf()
            }

            val diskList = ArrayList<Disk>()
            var row = movement.row - 1
            var column = movement.column - 1
            var filledDiskColor = board.getValueInPosition(row, column)
            while (!isEmptyPosition(filledDiskColor) && movement.disk.color != filledDiskColor) {
                diskList.add(board.getDiskInPosition(row, column))
                row--
                column--
                if (row >= 0 && column >= 0) {
                    filledDiskColor = board.getValueInPosition(row, column)
                }
            }

            if ((row < 0 || column < 0) || isEmptyPosition(filledDiskColor)) return listOf()

            return diskList
        }
    }
}
