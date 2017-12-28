package main.java.lt.jgreibus.ui;

import com.nomagic.magicdraw.ui.notification.Notification;
import com.nomagic.magicdraw.ui.notification.NotificationManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileChooser extends JPanel implements ActionListener {

    JButton openButton;
    JFileChooser fc;

    public FileChooser() {

        super(new BorderLayout());

        fc = new JFileChooser();
        fc.setDialogTitle("Select file for webapack stats");
        FileNameExtensionFilter filer = new FileNameExtensionFilter(".json", "json");
        fc.setFileFilter(filer);

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);

        add(buttonPanel, BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openButton) {
            int val = fc.showOpenDialog(com.nomagic.magicdraw.core.Application.getInstance().getMainFrame().getParent());


            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                NotificationManager.getInstance().openNotificationWindow(new Notification(null, "File " + file.getName() + " has been selected for webpack stats import."), true);
            } else {
                NotificationManager.getInstance().openNotificationWindow(new Notification(null, "Open dialog has been closed by user. File for webpack stats import is not selected."), false);
            }

        }

    }
}
