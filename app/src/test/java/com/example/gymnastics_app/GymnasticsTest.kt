package com.example.gymnastics_app

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GymnasticsTest {

    private lateinit var gymnastics: Gymnastics

    @Before
    fun setUp() {
        gymnastics = Gymnastics()
    }

    @Test
    fun `initial state is correct`() {
        assertEquals(0, gymnastics.score)
        assertEquals(1, gymnastics.currentElement)
        assertFalse(gymnastics.isDeductionTaken)
    }

    @Test
    fun `perform easy element`() {
        val result = gymnastics.perform()
        
        assertTrue(result.success)
        assertEquals(Zone.EASY, result.zone)
        assertFalse(result.isComplete)
        
        assertEquals(1, gymnastics.score)
        assertEquals(2, gymnastics.currentElement)
    }

    @Test
    fun `perform intermediate and advanced elements`() {
        // Skip easy zone (1..3)
        gymnastics.perform()
        gymnastics.perform()
        gymnastics.perform()
        
        // Element 4 is intermediate
        var result = gymnastics.perform()
        assertTrue(result.success)
        assertEquals(Zone.INTERMEDIATE, result.zone)
        
        // Skip intermediate zone (5..7)
        gymnastics.perform()
        gymnastics.perform()
        gymnastics.perform()
        
        // Element 8 is advanced
        result = gymnastics.perform()
        assertTrue(result.success)
        assertEquals(Zone.ADVANCED, result.zone)
    }

    @Test
    fun `score doesn't exceed max of 20`() {
        // Loop up to 10 elements (which is max per rules)
        for (i in 1..10) {
            gymnastics.perform()
        }
        
        assertTrue("Score should not exceed 20", gymnastics.score <= 20)
        
        // Let's force an extra perform if possible, to test bounds
        val result = gymnastics.perform()
        assertFalse("Should fail to perform past 10 elements", result.success)
        assertNull(result.zone)
    }

    @Test
    fun `apply deduction fails on first element`() {
        val result = gymnastics.applyDeduction()
        
        assertFalse(result)
        assertEquals(0, gymnastics.score)
        assertFalse(gymnastics.isDeductionTaken)
    }

    @Test
    fun `apply deduction reduces score by 2 but not below 0`() {
        // perform element 1 (+1 point)
        gymnastics.perform()
        
        val result = gymnastics.applyDeduction()
        assertTrue(result)
        assertEquals(0, gymnastics.score) // 1 - 2 = -1 -> coerced to 0
        assertTrue(gymnastics.isDeductionTaken)
    }

    @Test
    fun `apply deduction only works once`() {
        // perform up to element 4 to get some score (+1, +1, +1, +2 = 5 points)
        gymnastics.perform()
        gymnastics.perform()
        gymnastics.perform()
        gymnastics.perform()
        
        val firstDeduction = gymnastics.applyDeduction()
        assertTrue(firstDeduction)
        assertEquals(3, gymnastics.score) // 5 - 2 = 3
        
        val secondDeduction = gymnastics.applyDeduction()
        assertFalse(secondDeduction)
        assertEquals(3, gymnastics.score) // Score remains 3
    }

    @Test
    fun `perform fails if deduction is taken`() {
        gymnastics.perform() // element 1
        gymnastics.applyDeduction()
        
        val result = gymnastics.perform() // try to perform element 2
        
        assertFalse(result.success)
        assertFalse(result.isComplete)
        assertNull(result.zone)
    }

    @Test
    fun `reset restores initial state`() {
        gymnastics.perform()
        gymnastics.applyDeduction()
        
        gymnastics.reset()
        
        assertEquals(0, gymnastics.score)
        assertEquals(1, gymnastics.currentElement)
        assertFalse(gymnastics.isDeductionTaken)
    }
}
