package me.tatarka.nyandroid.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "org.intellij.sdk.settings.AppSettingsState",
        storages = @Storage("SdkSettingsPlugin.xml")
)
public class NyandroidAppSettings implements PersistentStateComponent<NyandroidAppSettings> {

    public String selectedIcon = "logcat";

    public static NyandroidAppSettings getInstance() {
        return ApplicationManager.getApplication().getService(NyandroidAppSettings.class);
    }

    @Override
    public @Nullable NyandroidAppSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull NyandroidAppSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }


}
