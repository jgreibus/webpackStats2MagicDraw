package lt.jgreibus;

import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.openapi.uml.SessionManager;
import com.nomagic.magicdraw.uml.Finder;
import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mddependencies.Dependency;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.NamedElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import com.nomagic.uml2.impl.ElementsFactory;
import lt.jgreibus.model.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateElements {

    private static final Project project = Application.getInstance().getProject();
    private static final ElementsFactory factory = project.getElementsFactory();
    private static final String stereotypeName = "webpackComponent";
    private static final Stereotype stereotype = StereotypesHelper.getStereotype(project, stereotypeName);

    public static void createElements(List elementList) {

        ArrayList<Component> list = new ArrayList<>(elementList);
        Element owner = (Element) SelectElementDlg.getSelectedElement();
        for (int i = 0; i < list.size(); i++) {
            createComponentElement(owner, list.get(i).name, list.get(i).id, list.get(i).identifier, list.get(i).documentation, list.get(i).version);
            System.out.println("Documentation: " + list.get(i).documentation);
            System.out.println("Version: " + list.get(i).version);
        }
    }

    private static void createComponentElement(Element owner, String subject, String id, String identifier, String documentation, String version) {

        SessionManager.getInstance().createSession(project, "Creating element with name: " + subject + " under " + owner.getHumanName());

        Class c = factory.createComponentInstance();
        c.setOwner(owner);
        String subjectItems[] = subject.split("/");
        c.setName(subjectItems[subjectItems.length - 1].replace("\"", ""));

        if (StereotypesHelper.canAssignStereotype(c, stereotype)) {
            StereotypesHelper.addStereotype(c, stereotype);
            StereotypesHelper.setStereotypePropertyValue(c, stereotype, "id", (Object) id);
            StereotypesHelper.setStereotypePropertyValue(c, stereotype, "identifier", (Object) identifier);
            StereotypesHelper.setStereotypePropertyValue(c, stereotype, "target_version", version);
        }
        ModelHelper.setComment(c, documentation);

        SessionManager.getInstance().closeSession(project);
    }

    public static void createDependencies(String id, ArrayList clients) {
        ArrayList<String> clientList = clients;
        for (int i = 0; i < clientList.size(); i++) {
            SessionManager.getInstance().createSession(project, "Relation creation");

            Dependency d = factory.createDependencyInstance();
            ModelHelper.setClientElement(d, getElementByID(clientList.get(i)));
            ModelHelper.setSupplierElement(d, getElementByID(id));
            d.setOwner(findElementOwnerByID(id));

            SessionManager.getInstance().closeSession(project);
        }
    }

    private static Element getElementByID(String id) {

        if (project != null) {
            Collection<NamedElement> candidates = Finder.byTypeRecursively().find(project, new java.lang.Class[]{com.nomagic.uml2.ext.magicdraw.components.mdbasiccomponents.Component.class}, false);
            for (Element c : candidates) {
                if (StereotypesHelper.hasStereotype(c, stereotype) &&
                        StereotypesHelper.getStereotypePropertyFirst(c, stereotype, "id").toString().equals(id)) {
                    return c;
                }
            }
        }
        return null;
    }

    private static Element findElementOwnerByID(String id) {
        if (project != null) {
            final Collection<Element> candidates = Finder.byTypeRecursively().find(project, new java.lang.Class[]{com.nomagic.uml2.ext.magicdraw.components.mdbasiccomponents.Component.class}, false);
            for (Element c : candidates) {
                if (StereotypesHelper.hasStereotype(c, stereotype)) {
                    return c.getOwner();
                }
            }
        }
        return null;
    }
}
