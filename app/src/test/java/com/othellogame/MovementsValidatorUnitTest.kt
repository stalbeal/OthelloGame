package com.othellogame

import org.junit.Assert.assertEquals
import org.junit.Test


class MovementsValidatorUnitTest {

    var board = Board()
    var movementsValidator = MovementsValidator(board)

    @Test
    fun `when player select a taken field the isTakenPosition must return true`() {
        assertEquals(true, movementsValidator.isTakenPosition(Movement(3,4, Disk("W"))))
    }

    @Test
    fun `when player select a empty field the isTakenPosition must return false`() {
        assertEquals(false, movementsValidator.isTakenPosition(Movement(6,7, Disk("W"))))
    }
}