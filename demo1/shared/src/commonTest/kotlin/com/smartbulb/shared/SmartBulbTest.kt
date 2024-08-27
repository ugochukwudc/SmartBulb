package com.smartbulb.shared

import kotlin.test.*

/**
 * Tests [SmartBulb]
 */
class SmartBulbTest {
    private val underTest: SmartBulb = SmartBulb()

    @Test
    fun bulbIsOffByDefault() = runSmartBulbTest {
        assertFalse { isOn() }
    }

    @Test
    fun bulbIsOnWhenTurnedOn() = runSmartBulbTest {
        turnOn()
        assertTrue { isOn() }
    }

    @Test
    fun bulbIsOff_toggleSwitch_turnsBulbOn() = runSmartBulbTest {
        toggleSwitch()
        assertTrue { isOn() }
    }

    @Test
    fun bulbIsOn_toggleSwitch_turnsBulbOff() = runSmartBulbTest {
        // Given
        turnOn()
        // When
        toggleSwitch()
        // Then
        assertFalse { isOn() }
    }

    @Test
    fun bulbHasNoPower_toggleBulbOnFromApp_bulbRemainsOff() = runSmartBulbTest {
        // Given bulb has no power

        // When
        appToggle()

        assertFalse { isOn() }
    }

    @Test
    fun bulbHasPowerAndIsOn_toggleFromApp_turnsBulbOff() = runSmartBulbTest {
        // Given
        toggleSwitch()

        // When
        appToggle()

        assertFalse { isOn() }
    }

    @Test
    fun bulbHasPowerAndIsOff_toggleFromApp_turnsBulbOn() = runSmartBulbTest {
        // Given
        toggleSwitch()
        appToggle()

        // When
        appToggle()

        assertTrue { isOn() }
    }

    @Test
    fun bulbHasPowerAndIsOn_toggleFromAppAndSwitch_turnsBulbOff() = runSmartBulbTest {
        // Given
        toggleSwitch()

        // When
        appToggle()
        toggleSwitch()

        // Then
        assertFalse { isOn() }
    }

    @Test
    fun bulbHasPowerAndIsOn_defaultColorIsYellow() = runSmartBulbTest {
        // Given
        toggleSwitch()

        // When

        // Then
        assertEquals(actual = color(), expected = BulbColor.YELLOW)
    }

    @Test
    fun bulbHasPowerAndIsOn_appSetsNewColor_colorChangesToTarget() = runSmartBulbTest {
        // Given
        toggleSwitch()

        BulbColor.entries.forEach { bulbColor ->
            // When
            appSetColor(bulbColor)

            // Then
            assertEquals(actual = color(), expected = bulbColor)
        }
    }

    @Test
    fun bulbIsOff_appFetchColor_returnsNothing() = runSmartBulbTest {
        // Given
        toggleSwitch()
        appToggle()

        // When
        // Then
        assertNull(color())
    }


    private fun runSmartBulbTest(block: SmartBulb.() -> Unit) {
        underTest.block()
    }
}