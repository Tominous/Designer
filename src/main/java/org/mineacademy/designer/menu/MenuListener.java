package org.mineacademy.designer.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.designer.button.Button;
import org.mineacademy.designer.model.UIClickLocation;
import org.mineacademy.remain.Remain;
import org.mineacademy.remain.util.CompatUtils;

/**
 * The bukkit listener responsible for menus to function.
 */
public final class MenuListener implements Listener {

	/**
	 * Handles closing menus
	 *
	 * @param event the event
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public final void onMenuClose(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player))
			return;

		final Player player = (Player) event.getPlayer();
		final Menu menu = Menu.getMenu(player);

		if (menu != null) {
			menu.onMenuClose(player, event.getInventory());

			player.removeMetadata(Menu.TAG_CURRENT, Remain.getPlugin());
		}
	}

	/**
	 * Handles clicking in menus
	 *
	 * @param event the event
	 */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public final void onMenuClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;

		final Player player = (Player) event.getWhoClicked();
		final Menu menu = Menu.getMenu(player);

		if (menu != null) {
			final ItemStack slotItem = event.getCurrentItem();
			final ItemStack cursor = event.getCursor();
			final Inventory clickedInv = Remain.getClickedInventory(event);

			final InventoryAction action = event.getAction();
			final UIClickLocation whereClicked = clickedInv != null ? (clickedInv.getType() == InventoryType.CHEST ? UIClickLocation.MENU : UIClickLocation.PLAYER_INVENTORY) : UIClickLocation.OUTSIDE;

			final boolean allowed = menu.isActionAllowed(whereClicked, event.getSlot(), slotItem, cursor);

			if (action.toString().contains("PICKUP") || action.toString().contains("PLACE") || action == InventoryAction.CLONE_STACK) {
				if (whereClicked == UIClickLocation.MENU) {
					try {
						final Button tool = menu.getButton(slotItem);

						if (tool != null)
							menu.onButtonClick(player, event.getSlot(), action, event.getClick(), tool);
						else
							menu.onMenuClick(player, event.getSlot(), action, event.getClick(), cursor, slotItem, !allowed);

					} catch (final Throwable t) {
						CompatUtils.tell(player, "&cOups! There was a problem with this menu! Please contact the administrator to review the console for details.");
						player.closeInventory();

						CompatUtils.error("Error clicking in menu " + menu, t);
					}
				}

				if (!allowed) {
					event.setResult(Result.DENY);

					player.updateInventory();
				}

			} else if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY || whereClicked != UIClickLocation.PLAYER_INVENTORY) {
				event.setResult(Result.DENY);

				player.updateInventory();
			}
		}
	}
}
