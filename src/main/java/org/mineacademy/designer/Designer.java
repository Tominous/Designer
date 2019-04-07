package org.mineacademy.designer;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.designer.menu.MenuListener;
import org.mineacademy.designer.model.UISound;
import org.mineacademy.designer.tool.ToolsListener;
import org.mineacademy.remain.Remain;
import org.mineacademy.remain.model.CompSound;

import lombok.Getter;
import lombok.Setter;

/**
 * Menu is a library to help you create beautiful and rich menus in your plugin.
 *
 * Please set your plugin asap with the setPlugin() method!
 *
 * @author kangarko
 */
public class Designer implements Listener {

	/**
	 * The default sound when switching between menues.
	 */
	@Getter
	@Setter
	private static UISound sound = new UISound(CompSound.NOTE_STICKS.getSound(), .4f);

	/**
	 * Determines if this class has been loaded, see {@link #setPlugin(JavaPlugin)}
	 */
	private static boolean classLoaded = false;

	/**
	 * Set the plugin that is utilizing this library as a source for Remain that we
	 * use Please call this as soon as possible in onEnable() in your plugin
	 *
	 * @param plugin your plugin
	 */
	public static final void setPlugin(JavaPlugin plugin) {
		classLoaded = true;

		Remain.setPlugin(plugin);

		Bukkit.getPluginManager().registerEvents(new MenuListener(), plugin);
		Bukkit.getPluginManager().registerEvents(new ToolsListener(), plugin);
	}

	// A smart check if the user really registered the plugin.
	static {
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (final InterruptedException e) {
				}

				Validate.isTrue(classLoaded, "A plugin is using Designer but forgot to call Designer.setPlugin() in its onEnable() first!");
			}

		}.start();
	}
}
