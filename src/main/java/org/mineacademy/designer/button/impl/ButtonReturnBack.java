package org.mineacademy.designer.button.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.designer.button.Button;
import org.mineacademy.designer.menu.Menu;
import org.mineacademy.designer.model.ItemCreator;
import org.mineacademy.remain.model.CompMaterial;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Represents a standardized button that will return back to the parent menu
 */
@RequiredArgsConstructor
@AllArgsConstructor
public final class ButtonReturnBack extends Button {

	/**
	 * The parent menu
	 */
	@NonNull
	private final Menu parentMenu;

	/**
	 * Should we make a new instance of the parent menu?
	 *
	 * False by default.
	 */
	private boolean makeNewInstance = false;

	/**
	 * The icon for this button
	 */
	@Override
	public final ItemStack getItem() {
		return ItemCreator.of(
				CompMaterial.OAK_DOOR,
				"&4&lReturn",
				"",
				"Return back.")
				.build().makeMenuTool();
	}

	/**
	 * Open the parent menu when clicked
	 */
	@Override
	public final void onClickedInMenu(Player pl, Menu menu, ClickType click) {
		if (makeNewInstance)
			parentMenu.newInstance().displayTo(pl);

		else
			parentMenu.displayTo(pl);
	}
}