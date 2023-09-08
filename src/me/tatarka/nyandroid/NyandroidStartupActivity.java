package me.tatarka.nyandroid;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NyandroidStartupActivity implements StartupActivity, StartupActivity.DumbAware {
    private static final String ICON_NYANDROID = "/file-icons/logcat.png";

    @Override
    public void runActivity(@NotNull Project project) {
        Icon icon = IconLoader.getIcon(ICON_NYANDROID);
        ToolWindow toolWindow = logcatToolWindow(project);
        if (toolWindow != null) {
            ApplicationManager.getApplication().invokeLater(() -> toolWindow.setIcon(icon));
        }
    }

    @Nullable
    private static ToolWindow logcatToolWindow(Project project) {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        return toolWindowManager.getToolWindow("Logcat");
    }
}
