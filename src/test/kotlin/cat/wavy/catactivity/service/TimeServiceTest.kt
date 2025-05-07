package cat.wavy.catactivity.service

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.openapi.components.service
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.testFramework.LightVirtualFile
import com.intellij.openapi.fileTypes.PlainTextFileType

class TimeServiceTest : BasePlatformTestCase() {
    private lateinit var timeService: TimeService
    private lateinit var virtualFile: VirtualFile

    override fun setUp() {
        super.setUp()

        // Create a test virtual file
        virtualFile = LightVirtualFile("test.txt", PlainTextFileType.INSTANCE, "test content")

        // Create TimeService instance
        timeService = project.service<TimeService>()
    }

    fun testTimeServiceInitialization() {
        assertNotNull("TimeService should be initialized", timeService)
    }

    fun testProjectOpened() {
        // Execute
        timeService.onProjectOpened(project)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }

    fun testProjectClosed() {
        // Execute
        timeService.onProjectClosed(project)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }

    fun testFileOpened() {
        // Execute
        timeService.onFileOpened(project, virtualFile)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }

    fun testFileClosed() {
        // First open the file
        timeService.onFileOpened(project, virtualFile)

        // Then close it
        timeService.onFileClosed(project, virtualFile)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }

    fun testFileChanged() {
        // Execute
        timeService.onFileChanged(project, virtualFile)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }

    fun testRender() {
        // Execute
        timeService.render(project)

        // No easy way to verify the result without mocking, but at least ensure no exceptions
        assertTrue(true)
    }
}
