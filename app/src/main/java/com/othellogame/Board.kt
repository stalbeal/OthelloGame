package com.othellogame

class Board {

    val size = 8
    val emptyIdentifier = "0"
    private val lightIdentifier = "W"
    private val darkIdentifier = "B"


    private val board = Array(size) { Array(size) { Disk() } }

    init {
        setFirstDarkPosition()
        setFirstLightField()
        setSecondDarkPosition()
        setSecondLightPosition()

    }

    fun getDiskInPosition(row: Int, column: Int): Disk = board[row][column]

    private fun setFirstLightField() {
        board[getInitialPosition()][getInitialPosition()] =
            Disk(lightIdentifier, true, Field(getInitialPosition(), getInitialPosition()))

    }

    private fun setFirstDarkPosition() {
        board[getInitialPosition() + 1][getInitialPosition()] =
            Disk(darkIdentifier, true, Field(getInitialPosition() + 1, getInitialPosition()))

    }

    private fun setSecondLightPosition() {
        board[getInitialPosition() + 1][getInitialPosition() + 1] =
            Disk(lightIdentifier, true, Field(getInitialPosition() + 1, getInitialPosition() + 1))
    }

    private fun setSecondDarkPosition() {
        board[getInitialPosition()][getInitialPosition() + 1] =
            Disk(darkIdentifier, true, Field(getInitialPosition(), getInitialPosition() + 1))

    }

    fun getValueInPosition(row: Int, column: Int): String {
        return board[row][column].color
    }

    private fun getInitialPosition() = (size / 2) - 1

    fun isTakenPosition(movement: Movement): Boolean {
        return getValueInPosition(movement.row, movement.column) != emptyIdentifier
    }

    fun isEmptyPosition(movement: Movement): Boolean {
        return getValueInPosition(movement.row, movement.column) == emptyIdentifier
    }

    fun isPositionInLeftBorder(column: Int): Boolean {
        return column == 0
    }

    fun isPositionInRightBorder(column: Int): Boolean {
        return column == size - 1
    }

    fun isPositionInTopBorder(row: Int): Boolean {
        return row == 0
    }

    fun isPositionInBottomBorder(row: Int): Boolean {
        return row == size - 1
    }

    fun isPositionInUpperLeftCorner(row: Int, column: Int): Boolean {
        return row == 0 && column == 0
    }

    fun isPositionInLowerLeftCorner(row: Int, column: Int): Boolean {
        return row == size - 1 && column == 0
    }

    fun isPositionInUpperRightCorner(row: Int, column: Int): Boolean {
        return row == size - 1 && column == 0
    }

    fun isPositionInLowerRightCorner(row: Int, column: Int): Boolean {
        return row == size - 1 && column == size - 1
    }

    fun printBoard(){
        var str = ""
        for (i in 0 until size) {
            for (j in 0 until size) {
                str += board[i][j].color
            }
            str+= "\n"
        }
        println(str)

    }


}

