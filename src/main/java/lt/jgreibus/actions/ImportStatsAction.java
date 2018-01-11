package lt.jgreibus.actions;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.ui.ProjectWindowsManager;

import javax.annotation.CheckForNull;
import java.awt.event.ActionEvent;

import static lt.jgreibus.ui.ImporterPanelConfigurator.addProjectWindow;

public class ImportStatsAction extends MDAction {
    public ImportStatsAction(String id, String name) {
        super(id, name, null, null);
    }

    @Override
    public void actionPerformed(@CheckForNull ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        ProjectWindowsManager projectManager = Application.getInstance().getMainFrame().getProjectWindowsManager();
        addProjectWindow(projectManager);

    }

    @Override
    public void updateState() {
        setEnabled(Application.getInstance().getProject() != null);
    }
}
