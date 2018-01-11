package lt.jgreibus.actions.configurators;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.MDActionsCategory;

public class ActionImportStatsConfigurator implements AMConfigurator {

    private NMAction action;

    public ActionImportStatsConfigurator(NMAction menuActions) {
        this.action = menuActions;
    }

    @Override
    public void configure(ActionsManager actionsManager) {

        ActionsCategory category = actionsManager.getCategory("Webpack");
        if (category == null) {
            category = new MDActionsCategory("Webpack", "Webpack");
            category.setNested(true);
            actionsManager.addCategory(category);
        }
        category.addAction(action);

    }

    @Override
    public int getPriority() {
        return AMConfigurator.HIGH_PRIORITY;
    }
}
