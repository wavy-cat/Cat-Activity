package me.rerere.discordij.setting

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "DiscordIJSettingProjectState",
    storages = [Storage("wave-discord.xml")]
)
class DiscordIJSettingProjectState : PersistentStateComponent<SettingState> {
    private val state = SettingState()

    override fun getState(): SettingState {
        return state
    }

    override fun loadState(state: SettingState) {
        XmlSerializerUtil.copyBean(state, this.state)
    }
}