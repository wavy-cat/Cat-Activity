package cat.wavy.catactivity.setting

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CustomApplicationValidationTest {
    @Test
    fun `application id accepts valid snowflake`() {
        assertEquals(1226060730273497098L, parseApplicationId("1226060730273497098"))
        assertTrue(isValidApplicationId("1226060730273497098"))
    }

    @Test
    fun `application id is trimmed`() {
        assertTrue(isValidApplicationId("  1226060730273497098  "))
    }

    @Test
    fun `application id rejects invalid values`() {
        assertFalse(isValidApplicationId(""))
        assertFalse(isValidApplicationId("   "))
        assertFalse(isValidApplicationId("1226060730273497"))
        assertFalse(isValidApplicationId("123456789012345678901"))
        assertFalse(isValidApplicationId("12260607302734970a"))
        assertFalse(isValidApplicationId("1226060730273497098L"))
        assertFalse(isValidApplicationId("99999999999999999999"))
    }

    @Test
    fun `icon url accepts http https and query`() {
        assertEquals("https://cdn.example.com/icon.webp", normalizeIconUrl("https://cdn.example.com/icon.webp"))
        assertEquals("http://example.com/a/b.webp", normalizeIconUrl("http://example.com/a/b.webp"))
        assertEquals(
            "https://cdn.example.com/icon.webp?token=abc",
            normalizeIconUrl("https://cdn.example.com/icon.webp?token=abc")
        )
        assertEquals("https://example.com:8443/icon.webp", normalizeIconUrl("https://example.com:8443/icon.webp"))
    }

    @Test
    fun `icon url rejects invalid values`() {
        assertNull(normalizeIconUrl(""))
        assertNull(normalizeIconUrl("   "))
        assertNull(normalizeIconUrl("/icon.webp"))
        assertNull(normalizeIconUrl("ftp://example.com/icon.webp"))
        assertNull(normalizeIconUrl("https://user:pass@example.com/icon.webp"))
        assertNull(normalizeIconUrl("https://example.com/icon.webp#frag"))
        assertNull(normalizeIconUrl("https:///icon.webp"))
    }

    @Test
    fun `server url still rejects query and fragment`() {
        assertEquals("https://example.com/base", normalizeUrl("https://example.com/base/"))
        assertNull(normalizeUrl("https://example.com/base?x=1"))
        assertNull(normalizeUrl("https://example.com/base#frag"))
    }
}
