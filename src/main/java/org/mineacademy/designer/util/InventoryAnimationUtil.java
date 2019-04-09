package org.mineacademy.designer.util;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.mineacademy.designer.menu.Menu;
import org.mineacademy.remain.util.RemainUtils;
import org.mineacademy.remain.util.MinecraftVersion;
import org.mineacademy.remain.util.ReflectionUtil;
import org.mineacademy.remain.util.MinecraftVersion.V;

import lombok.Getter;
import lombok.Setter;

/**
 * An utility class for sending animated inventory titles
 */
public class InventoryAnimationUtil {

	/**
	 * The default duration of the new animated title before
	 * it is reverted back to the old one
	 *
	 * Used in {@link #animateTitle(Menu, Player, String, String)}
	 */
	@Getter @Setter
	private static int durationTicks = 20;

	private static Map<UUID, BukkitTask> tasks = new ConcurrentHashMap<>();

	/**
	 * Sends an animated title to player for the {@link #durationTicks} duration. Colors are replaced
	 *
	 * @param menu the menu
	 * @param player the player
	 * @param animated the animated title
	 * @param old the old title
	 */
	public static final void animateTitle(Menu menu, Player player, String animated, String old) {
		animateTitle(menu, player, animated, old, durationTicks);
	}

	/**
	 * Sends an animated title to player. Colors are replaced.
	 *
	 * @param menu the menu
	 * @param player the playedr
	 * @param animated the animated title
	 * @param old the old title to revert to
	 * @param duration the duration in ticks
	 */
	public static final void animateTitle(Menu menu, Player player, String animated, String old, int duration) {
		Objects.requireNonNull(menu, "Menu == null");
		Objects.requireNonNull(player, "Player == null");
		Objects.requireNonNull(animated, "Title == null");
		Objects.requireNonNull(old, "Old Title == null");

		// Send the packet
		ReflectionUtil.updateInventoryTitle(player, MinecraftVersion.atLeast(V.v1_13) ? animated.replace("%", "%%") : animated);


		// Prevent flashing titles
		BukkitTask pending = tasks.get(player.getUniqueId());

		if (pending != null)
			pending.cancel();

		pending = RemainUtils.runDelayed(duration, () -> {
			final Menu futureMenu = Menu.getMenu(player);

			if (futureMenu != null && futureMenu.getClass().getName().equals(menu.getClass().getName()))
				ReflectionUtil.updateInventoryTitle(player, old);
		});

		final UUID uid = player.getUniqueId();

		tasks.put(uid, pending);

		// Prevent overloading the map so remove the key afterwards
		RemainUtils.runDelayed(duration + 1, () -> {
			if (tasks.containsKey(uid))
				tasks.remove(uid);
		});
	}
}
