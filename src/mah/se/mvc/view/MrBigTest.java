package mah.se.mvc.view;

import mah.se.mvc.view.MrBigViewWindowsWithFlowText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gustaf on 07/01/2016.
 */
public class MrBigTest extends JFrame {
    private MrBigViewWindowsWithFlowText mrBig;
    public MrBigTest(MrBigViewWindowsWithFlowText mrBig) {
        this.mrBig = mrBig;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(buttonPanel());
        pack();
        setPreferredSize(new Dimension(500, 500));
    }

    public JPanel buttonPanel() {
        JPanel panel = new JPanel();
        JButton setSize = new JButton("Set size: 200x200");
        setSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mrBig.setSize(new Dimension(1000, 1000));
            }
        });
        panel.add(setSize);
        return panel;
    }
}
