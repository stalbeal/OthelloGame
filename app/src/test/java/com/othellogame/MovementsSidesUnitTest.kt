package com.othellogame

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovementsSidesUnitTest {
    private val board = mockk<Board>()
    private val movement = mockk<Movement>()
    private val disk = mockk<Disk>()
    private lateinit var left: MovementsSide
    private lateinit var right: MovementsSide

    @Before
    fun setup() {
        every { disk.color } answers { "B" }
        every { board.isPositionInLeftBorder(any()) } answers { false }
        every { board.isPositionInRightBorder(any()) } answers { false }


        right = MovementsSide.Right(board, movement)
    }


    @Test
    fun `given a movement in the first column when getDisksToChangeInSide is called then it should return and empty array`() {
        every { movement.row } answers { 0 }
        every { board.isPositionInLeftBorder(0) } answers { true }

        val result = left.getDisksToChangeInSide()

        assert(result.isEmpty())
    }

    @Test
    fun `given a movement in column 2 row 0 with next positions as wwb when  getDisksToChangeInSide is called then it should return and empty array`() {
        every { movement.row } answers { 2 }
        every { movement.disk } answers { disk }
        every { board.isEmptyPosition(movement) } answers { false }
        every { board.isPositionInLeftBorder(2) } answers { false }
        every { board.getValueInPosition(0, 1) } answers { "W" }
        every { board.getValueInPosition(0, 0) } answers { "W" }
        every { board.getDiskInPosition(0, 1) } answers { Disk(color = "W") }
        every { board.getDiskInPosition(0, 0) } answers { Disk(color = "W") }

        val result = left.getDisksToChangeInSide()

        assert(result.isEmpty())
    }

    @Test
    fun `given a movement in column 3 row 0 with next positions as 0wwb when  getDisksToChangeInSide is called then it should return and empty array`() {
        every { board.isEmptyPosition(any()) } answers { true }
        every { board.getValueInPosition(0, 3) } answers { "0" }

        left = MovementsSide.Left(board, Movement(0, 3, Disk(color = "B")))

        every { board.isEmptyPosition(movement) } answers { false }
        every { board.isPositionInLeftBorder(3) } answers { false }
        every { board.getValueInPosition(0, 2) } answers { "W" }
        every { board.getValueInPosition(0, 1) } answers { "W" }
        every { board.getValueInPosition(0, 0) } answers { "0" }
        every { board.getDiskInPosition(0, 2) } answers { Disk(color = "W") }
        every { board.getDiskInPosition(0, 1) } answers { Disk(color = "W") }

        val result = left.getDisksToChangeInSide()

        assert(result.isEmpty())
    }

    @Test
    fun `given a movement in column 3 row 0 with next positions as bwwb when  getDisksToChangeInSide is called then it should return a list with two white disks`() {
        every { board.isEmptyPosition(any()) } answers { true }
        every { board.getValueInPosition(0, 3) } answers { "0" }

        left = MovementsSide.Left(board, Movement(0, 3, Disk(color = "B")))

        every { board.isEmptyPosition(movement) } answers { false }
        every { board.isPositionInLeftBorder(3) } answers { false }
        every { board.getValueInPosition(0, 2) } answers { "W" }
        every { board.getValueInPosition(0, 1) } answers { "W" }
        every { board.getValueInPosition(0, 0) } answers { "B" }
        every { board.getDiskInPosition(0, 2) } answers { Disk(color = "W") }
        every { board.getDiskInPosition(0, 1) } answers { Disk(color = "W") }

        val result = left.getDisksToChangeInSide()

        Assert.assertEquals(listOf(Disk(color = "W"), Disk(color = "W")), result)
    }

    @Test
    fun `given a movement in column 2 row 3 with next positions as WB when  right_getDisksToChangeInSide is called then it should return a list with one white disks`() {
        /*every { board.isEmptyPosition(any()) } answers { true }
        every { board.getValueInPosition(3, 2) } answers { "0" }// se asegura que la posicion a tomar este vacia

        right = MovementsSide.Right(board, Movement(3, 2, Disk(color = "B")))
        every { right.hasToValidateSideInMovement() } answers {true}

        every { board.isEmptyPosition(Movement(3, 2, Disk(color = "B"))) } answers { false }
        every { board.isPositionInRightBorder(3) } answers { false }
        every { board.getValueInPosition(3, 3) } answers { "W" }
        every { board.getValueInPosition(3, 4) } answers { "B" }
        //every { board.getValueInPosition(3, 2) } answers { "B" }

        every { board.getDiskInPosition(3, 3) } answers { Disk(color = "W") }
        */
        val b = Board()
        val r = MovementsSide.Right(b, Movement(3, 2, Disk(color = "B")))


        val result = r.getDisksToChangeInSide()

        Assert.assertEquals(listOf(Disk(color = "W")), result)
    }

}