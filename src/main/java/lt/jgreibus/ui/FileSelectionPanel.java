package lt.jgreibus.ui;

import com.nomagic.magicdraw.ui.notification.Notification;
import com.nomagic.magicdraw.ui.notification.NotificationManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static lt.jgreibus.ExtractElementData.parseStatsJSON;

public class FileSelectionPanel extends JPanel implements ActionListener {

    JButton openButton;
    JFileChooser fc;

    public FileSelectionPanel() {

        super(new BorderLayout());

        fc = new JFileChooser();
        fc.setDialogTitle("Select file for webapack stats:");
        FileNameExtensionFilter filer = new FileNameExtensionFilter(".json", "json");
        fc.setFileFilter(filer);

        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new JLabel("Select the Webpack Stats file"));
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
                parseStatsJSON(file);
            } else {
                NotificationManager.getInstance().openNotificationWindow(new Notification(null, "Open dialog has been closed by user. File for webpack stats import is not selected."), false);
            }

        }

    }
}
