package Util;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class ToastMessage {
	
	public static void showToastMessage(String message, int duration) {
        JDialog toast = new JDialog();
        toast.setUndecorated(true);
        toast.getContentPane().setBackground(Color.GRAY);
        toast.getContentPane().setLayout(new BorderLayout());
        toast.setSize(300, 50);
        int x = Variables.window.getLocationOnScreen().x + Variables.window.getWidth() / 2 - toast.getWidth() / 2;
        int y = Variables.window.getLocationOnScreen().y + Variables.window.getHeight() / 2 - toast.getHeight() / 2;
        toast.setLocation(x, y);
        JLabel toastLabel = new JLabel(message, JLabel.CENTER);
        toast.getContentPane().add(toastLabel, BorderLayout.CENTER);
        toast.setVisible(true);
        new Thread(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            toast.dispose();
        }).start();
    }
}
