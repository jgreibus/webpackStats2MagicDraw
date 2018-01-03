package main.java.lt.jgreibus;

import com.google.gson.*;
import com.nomagic.magicdraw.ui.notification.Notification;
import com.nomagic.magicdraw.ui.notification.NotificationManager;
import main.java.lt.jgreibus.model.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import static main.java.lt.jgreibus.CreateElements.createDependencies;
import static main.java.lt.jgreibus.CreateElements.createElements;

public class ExtractElementData {

    public static void parseStatsJSON(File statsFile) {

        JsonParser parser = new JsonParser();
        ArrayList<Component> componentList = new ArrayList<>();
        JsonArray modules = new JsonArray();

        try {
            Object object = parser.parse(new FileReader(statsFile));
            JsonObject json = (JsonObject) object;

            if (json.get("modules").toString().length() > 0) {
                modules = (JsonArray) json.get("modules");

                for (Object module : modules) {
                    JsonObject m = (JsonObject) module;
                    componentList.add(new Component(m.get("name").toString(),
                            m.get("id").toString(),
                            m.get("identifier").toString(),
                            extractMetadataValue(m, "description"),
                            extractMetadataValue(m, "target-version")));
                }
            }
        } catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
            NotificationManager.getInstance().openNotificationWindow(new Notification(null, e.getMessage()), false);
        }
        createElements(componentList);
        collectDependencies(modules);
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
        int initialIndex = 0;
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

        int endIndex = indexList.get((indexList.indexOf(initialIndex)) + 1);

        return sourceObject.substring(initialIndex, endIndex).replace("@" + metadatItem + " ", "");
    }
}
