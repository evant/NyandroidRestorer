package me.tatarka.nyandroid;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import me.tatarka.nyandroid.settings.NyandroidAppSettings;
import me.tatarka.nyandroid.settings.NyandroidIcon;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NyandroidToolWindowListener implements ToolWindowManagerListener {

    private static final String LOGCAT_ID = "Logcat";

    @Override
    public void toolWindowsRegistered(@NotNull List<String> ids, @NotNull ToolWindowManager toolWindowManager) {
        ToolWindowManagerListener.super.toolWindowsRegistered(ids, toolWindowManager);
        if (ids.contains(LOGCAT_ID)) {
            var selected = NyandroidAppSettings.getInstance().selectedIcon;
            updateIcon(toolWindowManager, selected);
        }
    }

    @Override
    public void toolWindowShown(@NotNull ToolWindow toolWindow) {
        ToolWindowManagerListener.super.toolWindowShown(toolWindow);
    }

    public static void updateIcon(String iconId) {
        var projectManager = ProjectManager.getInstance();
        for (var project : projectManager.getOpenProjects()) {
            var toolWindowManager = ToolWindowManager.getInstance(project);
            updateIcon(toolWindowManager, iconId);
        }
    }

    private static void updateIcon(ToolWindowManager toolWindowManager, String iconId) {
        var toolWindow = toolWindowManager.getToolWindow(LOGCAT_ID);
        if (toolWindow == null) {
            return;
        }
        var selected = NyandroidIcon.of(iconId);
        var selectedIcon = IconLoader.getIcon(selected.path, NyandroidToolWindowListener.class);

        ApplicationManager.getApplication().invokeLater(() -> {
            toolWindow.setIcon(selectedIcon);
        });
    }
}
