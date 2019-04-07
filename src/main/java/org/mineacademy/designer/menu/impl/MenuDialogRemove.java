package org.mineacademy.designer.menu.impl;

import org.bukkit.inventory.ItemStack;
import org.mineacademy.designer.button.Button;
import org.mineacademy.designer.button.impl.ButtonReturnBack;
import org.mineacademy.designer.button.impl.ButtonRemove.RemoveConfirmButton;
import org.mineacademy.designer.menu.Menu;
import org.mineacademy.designer.model.InventoryDrawer;

/**
 * A prepared menu to allow two-step object removal with a confirmation step
 */
public final class MenuDialogRemove extends Menu {

	/**
	 * The confirmation button that triggers the removal
	 */
	private final Button confirmButton;

	/**
	 * The return button
	 */
	private final Button returnButton;

	/**
	 * Create a new confirmation remove dialog
	 *
	 * @param parentMenu the parent menu
	 * @param confirmButton the remove button
	 */
	public MenuDialogRemove(Menu parentMenu, RemoveConfirmButton confirmButton) {
		this.confirmButton = confirmButton;
		this.returnButton = new ButtonReturnBack(parentMenu);
	}

	/**
	 * Returns the proper item at the correct slot
	 *
	 * @param slot the slot
	 * @return the item or null
	 */
	@Override
	public final ItemStack getItemAt(int slot) {
		if (slot == 9 + 3)
			return confirmButton.getItem();

		if (slot == 9 + 5)
			return returnButton.getItem();

		return null;
	}

	/**
	 * Draws the inventory, 3 rows
	 *
	 * @return the inventory drawer
	 */
	@Override
	protected final InventoryDrawer drawInventory() {
		return InventoryDrawer.of(9 * 3, getTitle());
	}

	/**
	 * Get the title for this menu
	 *
	 * @return "Confirm removal"
	 */
	@Override
	public final String getTitle() {
		return "&0Confirm removal";
	}
}
