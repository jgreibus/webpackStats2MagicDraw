package main.java.lt.jgreibus;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;
import main.java.lt.jgreibus.model.Component;

import java.util.ArrayList;
import java.util.List;

public class CreateElements {

    private static final String stereotypeName = "webpackComponent";
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

        Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName);


        Class c = factory.createComponentInstance();
        c.setOwner(owner);
        String subjectItems[] = subject.split("/");
        c.setName(subjectItems[subjectItems.length - 1].replace("\"", ""));
        if (StereotypesHelper.canAssignStereotype(c, stereotype)) {
            StereotypesHelper.addStereotype(c, stereotype);
            StereotypesHelper.setStereotypePropertyValue(c, stereotype, "id", (Object) id);
            StereotypesHelper.setStereotypePropertyValue(c, stereotype, "identifier", (Object) identifier);
        }

        SessionManager.getInstance().closeSession(project);

    }

    public static void createDependencyRelation() {

    }

}
