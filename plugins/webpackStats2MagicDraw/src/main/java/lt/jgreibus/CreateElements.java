package main.java.lt.jgreibus;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.impl.ElementsFactory;
import main.java.lt.jgreibus.model.Component;

import java.util.ArrayList;
import java.util.List;

public class CreateElements {

    private static final Project project = Application.getInstance().getProject();

    public static void createElements(List elementList) {

        ArrayList<Component> list = new ArrayList<>(elementList);
        Element owner = (Element) SelectElementDlg.getSelectedElement();
        for (int i = 0; i < list.size(); i++) {
            createComponentElement(owner, list.get(i).name, list.get(i).id, list.get(i).identifier);
        }
    }

    private static void createComponentElement(Element owner, String subject, String id, String identifier) {

        ElementsFactory factory = project.getElementsFactory();
        SessionManager.getInstance().createSession(project, "createElement");

        Class c = factory.createComponentInstance();
        c.setOwner(owner);
        SessionManager.getInstance().closeSession(project);

    }

    public static void createDependencyRelation() {

    }

}
