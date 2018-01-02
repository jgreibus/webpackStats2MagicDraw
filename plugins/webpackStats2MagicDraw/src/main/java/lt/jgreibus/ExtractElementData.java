package main.java.lt.jgreibus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

            modules = (JsonArray) json.get("modules");

            for (Object module : modules) {
                JsonObject m = (JsonObject) module;
                componentList.add(new Component(m.get("name").toString(), m.get("id").toString(), m.get("identifier").toString()));


            }

        } catch (FileNotFoundException e) {
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
}
