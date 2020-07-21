package com.othellogame

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {


    private lateinit var board: Board

    @Before
    fun setup() {
        board = Board()
    }

    @Test
    fun `when board is init the first disk color must be W`() {

        assertEquals("W", board.getValueInPosition(3,3))
    }

    @Test
    fun `when board is init the second disk color must be B`() {

        assertEquals("B", board.getValueInPosition(4,3))
    }

    @Test
    fun `when board is init the third disk color must be B`() {

        assertEquals("B", board.getValueInPosition(3,4))
    }

    @Test
    fun `when board is init the fourth disk color must be B`() {

        assertEquals("W", board.getValueInPosition(4,4))
    }



}

