package me.tatarka.nyandroid;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class NyandroidToolWindowListener implements ToolWindowManagerListener {

    private static final String ICON_NYANDROID = "/file-icons/logcat.png";
    private static final String LOGCAT_ID = "Logcat";

    private final Icon icon = IconLoader.getIcon(ICON_NYANDROID, getClass());

    @Override
    public void toolWindowsRegistered(@NotNull List<String> ids, @NotNull ToolWindowManager toolWindowManager) {
        ToolWindowManagerListener.super.toolWindowsRegistered(ids, toolWindowManager);
        if (ids.contains(LOGCAT_ID)) {
            var toolWindow = requireNonNull(toolWindowManager.getToolWindow(LOGCAT_ID));
            ApplicationManager.getApplication().invokeLater(() -> toolWindow.setIcon(icon));
        }
    }

    @Override
    public void toolWindowShown(@NotNull ToolWindow toolWindow) {
        ToolWindowManagerListener.super.toolWindowShown(toolWindow);
    }
}
