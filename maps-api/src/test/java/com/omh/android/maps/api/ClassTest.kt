package com.omh.android.maps.api

import com.omh.android.maps.api.extensions.tag
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ClassTest {

    class ShortClassName
    class ThisIsALongClassNameThatIsGreaterThan23Characters

    @Test
    fun `given a class with short name, when gets the tag, then a complete name is returned`() {
        val clazz = ShortClassName::class.java
        val tag = clazz.tag()
        assertEquals(clazz.simpleName, tag)
    }

    @Test
    fun `given a class with long name, when gets the tag, then a substring name is returned`() {
        val clazz = ThisIsALongClassNameThatIsGreaterThan23Characters::class.java
        val tag = clazz.tag()
        assertEquals("ThisIsALongClassNameTha", tag)
    }

    @Test
    fun `given a class with long name, when gets the tag, then a substring is different than simple name`() {
        val clazz = ThisIsALongClassNameThatIsGreaterThan23Characters::class.java
        val tag = clazz.tag()
        assertNotEquals(clazz.simpleName, tag)
    }
}
