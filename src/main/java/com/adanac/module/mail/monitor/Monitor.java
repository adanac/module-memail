package com.adanac.module.mail.monitor;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用于开启获取进程信息
 * @author adanac
 */
public class Monitor {

	public Monitor() {
		boolean[] on_off = { true };
		new Thread(new ProcessInfo(on_off)).start();
		new Thread(new KeyboardHook(on_off)).start();
		new Thread(new MouseHook(on_off)).start();
		final TrayIcon trayIcon;

		if (SystemTray.isSupported()) {

			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().getImage(".//lib//monitor.png");

			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting...");
					System.exit(0);
				}
			};

			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);

			trayIcon = new TrayIcon(image, "monitor", popup);

			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					trayIcon.displayMessage("Action Event", "An Action Event Has Been Peformed!",
							TrayIcon.MessageType.INFO);
				}
			};

			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(actionListener);

			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new Monitor();
	}

}
