package main.java.lt.jgreibus;

import com.nomagic.magicdraw.ui.dialogs.MDDialogParentProvider;
import com.nomagic.magicdraw.ui.dialogs.SelectElementInfo;
import com.nomagic.magicdraw.ui.dialogs.SelectElementTypes;
import com.nomagic.magicdraw.ui.dialogs.selection.ElementSelectionDlg;
import com.nomagic.magicdraw.ui.dialogs.selection.ElementSelectionDlgFactory;
import com.nomagic.magicdraw.ui.dialogs.selection.TypeFilter;
import com.nomagic.magicdraw.ui.dialogs.selection.TypeFilterImpl;
import com.nomagic.magicdraw.uml.BaseElement;
import com.nomagic.magicdraw.uml.ClassTypes;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class SelectElementDlg {

    public static BaseElement getSelectedElement() {

        Frame dialogParent = MDDialogParentProvider.getProvider().getDialogParent();
        ElementSelectionDlg dlg = ElementSelectionDlgFactory.create(dialogParent);

        List<Class> types = ClassTypes.getSubtypes(Package.class);

        SelectElementTypes selectElementTypes = new SelectElementTypes(types, types, null, types);
        TypeFilter selectableFilter = new TypeFilterImpl(selectElementTypes.select) {
            @Override
            public boolean accept(@Nonnull BaseElement baseElement, boolean checkType) {
                return super.accept(baseElement, checkType);
            }
        };

        TypeFilter visibleFilter = new TypeFilterImpl(selectElementTypes.display) {
            @Override
            public boolean accept(@Nonnull BaseElement baseElement, boolean checkType) {
                return super.accept(baseElement, checkType);
            }
        };

        ElementSelectionDlgFactory.initSingle(
                dlg,
                new SelectElementInfo(false, false, null, true),
                visibleFilter,
                selectableFilter,
                types,
                null);

        dlg.setVisible(true);

        if (dlg.isOkClicked()) {
            return dlg.getSelectedElement();
        }
        return null;
    }
}
