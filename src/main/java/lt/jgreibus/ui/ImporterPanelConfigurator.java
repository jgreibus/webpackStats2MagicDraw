package lt.jgreibus.ui;

import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.ui.*;

public class ImporterPanelConfigurator implements ProjectWindowsConfigurator {

    @Override
    public void configure(Project project, ProjectWindowsManager projectWindowsManager) {
        addProjectWindow(projectWindowsManager);

    }

    public static ProjectWindow addProjectWindow(ProjectWindowsManager projectWindowsManager) {
        ProjectWindow projectWindow = new ProjectWindow(createWindowComponentInfo(), new ImporterPanelContent());
        projectWindowsManager.addWindow(projectWindow);

        return projectWindow;
    }

    private static WindowComponentInfo createWindowComponentInfo() {
        return new WindowComponentInfo("WEBPACK_PROJECT_WINDOW_PANEL_ID",
                "Webpack Stats Import",
                null,
                WindowsManager.SIDE_EAST,
                WindowsManager.STATE_DOCKED, false);
    }
}
