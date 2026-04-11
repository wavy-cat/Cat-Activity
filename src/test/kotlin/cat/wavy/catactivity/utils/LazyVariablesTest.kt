package cat.wavy.catactivity.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class LazyVariablesTest {
    @Test
    fun `resolve returns eager value`() {
        val variables = LazyVariables().apply {
            put("%projectName%", "Cat Activity")
        }

        val result = variables.resolve("%projectName%")

        assertEquals("Cat Activity", result)
    }

    @Test
    fun `resolve caches lazy value`() {
        val calls = AtomicInteger(0)
        val variables = LazyVariables().apply {
            putLazy("%branch%") {
                calls.incrementAndGet()
                "main"
            }
        }

        val first = variables.resolve("%branch%")
        val second = variables.resolve("%branch%")

        assertEquals("main", first)
        assertEquals("main", second)
        assertEquals(1, calls.get())
    }

    @Test
    fun `resolve returns null for unknown variable`() {
        val variables = LazyVariables()

        val result = variables.resolve("%missing%")

        assertEquals(null, result)
    }

    @Test
    fun `replaceIn replaces eager and lazy variables`() {
        val variables = LazyVariables().apply {
            put("%projectName%", "Cat Activity")
            putLazy("%branch%") { "main" }
        }

        val result = variables.replaceIn("Working on %projectName% [%branch%]")

        assertEquals("Working on Cat Activity [main]", result)
    }

    @Test
    fun `replaceIn evaluates lazy provider once for repeated variable`() {
        val calls = AtomicInteger(0)
        val variables = LazyVariables().apply {
            putLazy("%branch%") {
                calls.incrementAndGet()
                "main"
            }
        }

        val result = variables.replaceIn("%branch% -> %branch%")

        assertEquals("main -> main", result)
        assertEquals(1, calls.get())
    }

    @Test
    fun `replaceIn leaves unknown variables unchanged`() {
        val variables = LazyVariables().apply {
            put("%projectName%", "Cat Activity")
        }

        val result = variables.replaceIn("%projectName% / %missing%")

        assertEquals("Cat Activity / %missing%", result)
    }

    @Test
    fun `replaceVariables delegates to replaceIn`() {
        val variables = LazyVariables().apply {
            put("%projectName%", "Cat Activity")
        }

        val result = "Project: %projectName%".replaceVariables(variables)

        assertEquals("Project: Cat Activity", result)
    }
}
