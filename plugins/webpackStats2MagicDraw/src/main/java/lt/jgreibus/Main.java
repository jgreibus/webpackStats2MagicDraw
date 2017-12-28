package main.java.lt.jgreibus;

import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.plugins.Plugin;
import main.java.lt.jgreibus.actions.ImportStatsAction;
import main.java.lt.jgreibus.actions.configurators.ActionImportStatsConfigurator;

public class Main extends Plugin
{
	public static boolean initialized;
	
	@Override
	public void init()
	{
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();
		manager.addMainMenuConfigurator(new ActionImportStatsConfigurator(getMenuActions()));

	}

	@Override
	public boolean close()
	{
		return true;
	}

	@Override
	public boolean isSupported()
	{
		return true;
	}

	private NMAction getMenuActions() {
		ActionsCategory category = new ActionsCategory(null, "");
		category.addAction(new ImportStatsAction("ImportWebpackStats", "Import WebPack Stats"));
		return category;
	}
}
