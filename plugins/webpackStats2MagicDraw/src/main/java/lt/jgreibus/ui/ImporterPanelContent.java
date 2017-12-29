package main.java.lt.jgreibus.ui;

import com.nomagic.magicdraw.ui.browser.WindowComponentContent;

import javax.annotation.CheckForNull;
import javax.swing.*;
import java.awt.*;

public class ImporterPanelContent implements WindowComponentContent {

    private JPanel mPanel;

    public ImporterPanelContent() {
        mPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mPanel.add(new FileSelectionPanel());
    }

    @Override
    public Component getWindowComponent() {
        return mPanel;
    }

    @CheckForNull
    @Override
    public Component getDefaultFocusComponent() {
        return null;
    }
}
