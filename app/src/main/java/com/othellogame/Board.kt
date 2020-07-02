package com.othellogame

class Board {

    private val height = 8
    val emptyIdentifier = "0"
    private val lightIdentifier = "W"
    private val darkIdentifier = "B"


    var board = Array(height) { Array(height) { Field(false, Disk()) } }

    init {
        setFirstDarkPosition()
        setFirstLightField()
        setSecondDarkPosition()
        setSecondLightPosition()

    }

    fun setFirstLightField() {
        board[getInitialPosition()][getInitialPosition()].isFilled = true
        board[getInitialPosition()][getInitialPosition()].disk.color = lightIdentifier
    }

    fun setFirstDarkPosition() {
        board[getInitialPosition() + 1][getInitialPosition()].isFilled = true
        board[getInitialPosition() + 1][getInitialPosition()].disk.color = darkIdentifier
    }

    fun setSecondLightPosition() {
        board[getInitialPosition() + 1][getInitialPosition() + 1].isFilled = true
        board[getInitialPosition() + 1][getInitialPosition() + 1].disk.color = lightIdentifier
    }

    fun setSecondDarkPosition() {
        board[getInitialPosition()][getInitialPosition() + 1].isFilled = true
        board[getInitialPosition()][getInitialPosition() + 1].disk.color = darkIdentifier
    }

    fun getValueInPosition(row: Int, column: Int): String {
        return board[row][column].disk.color
    }

    fun getInitialPosition() = (height / 2) - 1


}

