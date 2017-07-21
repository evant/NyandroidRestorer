package me.tatarka.nyandroid;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class NyandroidProject implements ProjectComponent {
    private static final String ICON_NYANDROID = "/file-icons/logcat.png";
    private final Project project;

    public NyandroidProject(Project project) {
        this.project = project;
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "NyandroidProject";
    }

    @Override
    public void projectOpened() {
        Icon icon = IconLoader.getIcon(ICON_NYANDROID);
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow("Logcat");
        if (toolWindow != null) {
            toolWindow.setIcon(icon);
        }
    }

    @Override
    public void projectClosed() {
        // called when project is being closed
    }
}
