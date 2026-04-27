package com.example.gymnastics_app

enum class Zone(val range: IntRange, val points: Int) {
    EASY(1..3, 1),
    INTERMEDIATE(4..7, 2),
    ADVANCED(8..10, 3)
}

data class PerformResult(val success: Boolean, val zone: Zone?, val isComplete: Boolean)

class Gymnastics {
    var score: Int = 0
        private set
    var currentElement: Int = 1
        private set
    var isDeductionTaken: Boolean = false
        private set

    fun perform(): PerformResult {
        val zone: Zone? = Zone.entries.find { currentElement in it.range }
        if (isDeductionTaken || zone == null) return PerformResult(false, null, false)

        score = (score+zone.points).coerceAtMost(20)
        currentElement = (currentElement+1).coerceAtMost(10)
        return PerformResult(true, zone, currentElement == 10 && score == 20)
    }
    fun applyDeduction(): Boolean {
        if (currentElement == 1 || isDeductionTaken || score == 20) return false
        score = (score-2).coerceAtLeast(0)
        isDeductionTaken = true
        return true
    }
    fun reset() {
        score = 0
        currentElement = 1
        isDeductionTaken = false
    }
}