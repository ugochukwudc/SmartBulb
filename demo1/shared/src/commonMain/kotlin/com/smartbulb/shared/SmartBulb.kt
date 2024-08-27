package com.smartbulb.shared

/**
 * A smart bulb is a bulb that is smart.
 *
 * It can be either on or off.
 * When on, it supports several Colors, WHITE, YELLOW, RED, BLUE, GREEN,
 * It can be toggled on/off via a Switch or an application
 * User can query the current state of the bulb, lifetime hours while on, hours since last off,
 * how many times it comes for that particular day
 */
class SmartBulb {
    private var state: Boolean = false
    private var hasPower: Boolean = false
    private var color: BulbColor = BulbColor.YELLOW
    fun isOn(): Boolean {
        return state
    }

    /**
     * This is a test only method
     */
    fun turnOn() {
        state = true
    }

    fun toggleSwitch() {
        hasPower = !hasPower
        if (hasPower) {
            state = !state
        }
    }

    fun appToggle() {
        if (hasPower) {
            state = !state
        }
    }

    fun appSetColor(color: BulbColor) {
        if (hasPower && state) {
            this.color = color
        }
    }

    fun color(): BulbColor? {
        return if (state) {
            color
        } else {
            null
        }
    }

}