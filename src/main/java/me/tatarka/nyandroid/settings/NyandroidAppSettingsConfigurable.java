package me.tatarka.nyandroid.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.SimpleListCellRenderer;
import com.intellij.util.ui.FormBuilder;
import me.tatarka.nyandroid.NyandroidToolWindowListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

import static com.intellij.openapi.util.NlsContexts.ConfigurableName;

public class NyandroidAppSettingsConfigurable implements Configurable {

    private SettingsComponent settingsComponent;

    @Override
    public @ConfigurableName String getDisplayName() {
        return "Nyandroid Restorer";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new SettingsComponent();
        return settingsComponent.component;
    }

    @Override
    public boolean isModified() {
        var settings = NyandroidAppSettings.getInstance();
        return !Objects.equals(settings.selectedIcon, settingsComponent.selectedIcon().id);
    }

    @Override
    public void apply() throws ConfigurationException {
        var settings = NyandroidAppSettings.getInstance();
        settings.selectedIcon = settingsComponent.selectedIcon().id;
        NyandroidToolWindowListener.updateIcon(settings.selectedIcon);
    }

    @Override
    public void reset() {
        var settings = NyandroidAppSettings.getInstance();
        settingsComponent.setSelectedIcon(settings.selectedIcon);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }

    private static class SettingsComponent {
        private final JPanel component;
        private final ComboBox<NyandroidIcon> iconDropdown;

        private SettingsComponent() {
            iconDropdown = new ComboBox<>(NyandroidIcon.ICONS);
            iconDropdown.setRenderer(new SimpleListCellRenderer<>() {
                @Override
                public void customize(@NotNull JList<? extends NyandroidIcon> list, NyandroidIcon value, int index, boolean selected, boolean hasFocus) {
                    setIcon(IconLoader.getIcon(value.path, getClass()));
                    setText(value.name);
                }
            });
            component = FormBuilder.createFormBuilder()
                    .addLabeledComponent("Logcat icon", iconDropdown)
                    .addComponentFillVertically(new JPanel(), 0)
                    .getPanel();
        }

        void setSelectedIcon(String id) {
            var selected = NyandroidIcon.of(id);
            iconDropdown.setItem(selected);
        }

        NyandroidIcon selectedIcon() {
            return iconDropdown.getItem();
        }
    }
}
