package lt.jgreibus;

import com.google.gson.*;
import com.nomagic.magicdraw.ui.notification.Notification;
import com.nomagic.magicdraw.ui.notification.NotificationManager;
import lt.jgreibus.model.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static lt.jgreibus.CreateElements.createDependencies;
import static lt.jgreibus.CreateElements.createElements;

public class ExtractElementData {

    public static void parseStatsJSON(File statsFile) {

        JsonParser parser = new JsonParser();

        try {
            Object object = parser.parse(new FileReader(statsFile));
            JsonObject json = (JsonObject) object;

            collectComponentList(json);
            if (extractModules(json) == null) {
                throw new NullPointerException();
            } else {
                collectDependencies(extractModules(json));
            }
        } catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
            NotificationManager.getInstance().openNotificationWindow(new Notification(null, e.getMessage()), false);
        }
    }

    private static void collectComponentList(JsonObject json) {
        ArrayList<Component> componentList = new ArrayList<>();

        if (extractModules(json) == null) {
            throw new NullPointerException();
        } else {
            for (Object module : extractModules(json)) {
                JsonObject m = (JsonObject) module;
                componentList.add(new Component(m.get("name").toString(),
                        m.get("id").toString(),
                        m.get("name").toString(),
                        extractMetadataValue(m, "description"),
                        extractMetadataValue(m, "target-version")));
            }
            if (componentList.size() > 0) createElements(componentList);
        }
    }

    private static JsonArray extractModules(JsonObject json) {

        JsonArray modules;
        if (json.get("modules").toString().length() > 0) {
            modules = (JsonArray) json.get("modules");
            return modules;
        }
        return null;
    }

    private static void collectDependencies(JsonArray modules) {

        for (Object module : modules) {
            ArrayList<String> clients = new ArrayList<>();
            JsonObject m = (JsonObject) module;
            JsonArray reasons = m.getAsJsonArray("reasons");

            for (Object reason : reasons) {
                JsonObject r = (JsonObject) reason;
                clients.add(r.get("moduleId").toString());
            }
            if (clients.size() > 0) createDependencies(m.get("id").toString(), clients);
        }
    }

    private static String extractMetadataValue(JsonObject source, String metadatItem) {
        String sourceObject = source.get("source").getAsString();
        ArrayList<Integer> indexList = new ArrayList<>();
        int initialIndex;
        if (sourceObject.contains("@" + metadatItem)) {
            initialIndex = sourceObject.indexOf("@" + metadatItem);
        } else {
            return "";
        }
        char ch = '@';

        for (int i = 0; i < sourceObject.length(); i++) {
            if (sourceObject.charAt(i) == ch) {
                indexList.add(i);
            }
        }

        int endIndex = indexList.get((indexList.indexOf(initialIndex)) + 1) - 5;

        return sourceObject.substring(initialIndex, endIndex).replace("@" + metadatItem + " ", "");
    }
}
