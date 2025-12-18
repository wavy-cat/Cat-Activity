package cat.wavy.catactivity.setting

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "CatActivitySettingProjectState", storages = [Storage("cat-activity.xml")]
)
@Service(Service.Level.PROJECT)
class CatActivitySettingProjectState : PersistentStateComponent<SettingState> {
    private val state = SettingState()

    override fun getState(): SettingState {
        return state
    }

    override fun loadState(state: SettingState) {
        XmlSerializerUtil.copyBean(state, this.state)
    }
}

@State(
    name = "CatActivitySettingAppState", storages = [Storage("cat-activity-defaults.xml")]
)
@Service(Service.Level.APP)
class CatActivitySettingAppState : PersistentStateComponent<DefaultsState> {
    private val state = DefaultsState()

    override fun getState(): DefaultsState {
        return state
    }

    override fun loadState(state: DefaultsState) {
        XmlSerializerUtil.copyBean(state, this.state)
    }
}