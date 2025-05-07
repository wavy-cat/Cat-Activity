package cat.wavy.catactivity.action

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.components.service
import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import cat.wavy.catactivity.setting.Details

class SelectShowProjectTest : BasePlatformTestCase() {
    private var action: SelectShowProject = SelectShowProject()

    fun testIsSelected() {
        // Get the setting state
        val settingState = project.service<CatActivitySettingProjectState>().state

        // Set up the state for testing
        settingState.isEnabled = true
        settingState.details = Details.Project

        // Create a data context that provides the project
        val dataContext = SimpleDataContext.builder()
            .add(CommonDataKeys.PROJECT, project)
            .build()

        // Create a mock action event
        val presentation = Presentation()
        val actionEvent = AnActionEvent.createFromDataContext("test", presentation, dataContext)

        // Test that isSelected returns true when details is Project and isEnabled is true
        assertTrue("Action should be selected when details is Project and isEnabled is true", 
                  action.isSelected(actionEvent))

        // Change the state and test again
        settingState.details = Details.File
        assertFalse("Action should not be selected when details is not Project", 
                   action.isSelected(actionEvent))

        settingState.details = Details.Project
        settingState.isEnabled = false
        assertFalse("Action should not be selected when isEnabled is false", 
                   action.isSelected(actionEvent))
    }

    fun testSetSelected() {
        // Get the setting state
        val settingState = project.service<CatActivitySettingProjectState>().state

        // Set up the state for testing
        settingState.isEnabled = false
        settingState.details = Details.File

        // Create a data context that provides the project
        val dataContext = SimpleDataContext.builder()
            .add(CommonDataKeys.PROJECT, project)
            .build()

        // Create a mock action event
        val presentation = Presentation()
        val actionEvent = AnActionEvent.createFromDataContext("test", presentation, dataContext)

        // Test that setSelected updates the state correctly
        action.setSelected(actionEvent, true)

        assertEquals("Details should be set to Project", Details.Project, settingState.details)
        assertTrue("isEnabled should be set to true", settingState.isEnabled)

        // Test that setSelected does nothing when state is false
        settingState.isEnabled = false
        settingState.details = Details.File

        action.setSelected(actionEvent, false)

        assertEquals("Details should not change when state is false", Details.File, settingState.details)
        assertFalse("isEnabled should not change when state is false", settingState.isEnabled)
    }
}
