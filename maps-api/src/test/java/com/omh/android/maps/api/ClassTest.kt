/*
 * Copyright 2023 Open Mobile Hub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.omh.android.maps.api

import com.omh.android.maps.api.extensions.tag
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

internal class ClassTest {

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
