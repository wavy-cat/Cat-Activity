package cat.wavy.catactivity.service

import cat.wavy.catactivity.setting.CatActivitySettingProjectState
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

class WelcomeService {
    private object ShowOnlyIde : AnAction("Only IDE") {
        override fun actionPerformed(e: AnActionEvent) {
            println("Selected Only IDE")
            // Code to be executed when the 'Yes' button is pressed
        }
    }

    private object ShowOnlyProject : AnAction("Only Project") {
        override fun actionPerformed(e: AnActionEvent) {
            println("Selected No")
            // Code to be executed when the 'No' button is pressed
        }
    }

    private object ShowFull : AnAction("Project and Current File") {
        override fun actionPerformed(e: AnActionEvent) {
            println("Selected No")
            // Code to be executed when the 'No' button is pressed
        }
    }

    companion object {
        fun welcomeAlert(project: Project) {
            // Perhaps this is a bad implementation. WavyCat, think about it.
            val configState = project.service<CatActivitySettingProjectState>().state

            if (configState.firstInit) {
                val groupId = "Cat Activity Notification"
                val title = "Welcome to Cat Activity!"
                val content = "What information do you want to display on your profile?"
                val notification = Notification(groupId, title, content, NotificationType.INFORMATION)

                notification.addAction(ShowOnlyIde)
                notification.addAction(ShowOnlyProject)
                notification.addAction(ShowFull)

                Notifications.Bus.notify(notification)

                configState.firstInit = false
            }
        }
    }
}