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

import static main.java.lt.jgreibus.CreateElements.createElements;

public class ExtractElementData {

    public static void parseStatsJSON(File statsFile) {

        JsonParser parser = new JsonParser();
        ArrayList<Component> componentList = new ArrayList<>();

        try {
            Object object = parser.parse(new FileReader(statsFile));
            JsonObject json = (JsonObject) object;

            JsonArray modules = (JsonArray) json.get("modules");

            for (Object module : modules) {
                JsonObject m = (JsonObject) module;
                componentList.add(new Component(m.get("name").toString(), m.get("id").toString(), m.get("identifier").toString()));
                System.out.println("===============");
                System.out.println(m.get("id"));
                System.out.println(m.get("identifier"));
                JsonArray reasons = m.getAsJsonArray("reasons");

                for (Object reason : reasons) {
                    System.out.println(reason);
                }
            }

        } catch (FileNotFoundException e) {
            NotificationManager.getInstance().openNotificationWindow(new Notification(null, e.getMessage()), false);
        }

        createElements(componentList);

        for (int i = 0; i < componentList.size(); i++) {
            System.out.println("=================");
            System.out.println(componentList.get(i).id + " " + componentList.get(i).name + " || " + componentList.get(i).identifier);
        }
    }

}
