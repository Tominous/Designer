package org.mineacademy.designer.menu.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.designer.button.Button;
import org.mineacademy.designer.menu.Menu;
import org.mineacademy.designer.model.ItemCreator;
import org.mineacademy.designer.model.UIQuantity;

/**
 * Advanced menu concept allowing to change quality of an item
 * by more than 1 on a single click.
 *
 * For example: You want to chance the spawn percentage from 1% to 100%
 * so you set the editing quantity to 20 and you only need to click the item
 * 5 times instead of 99 times.
 *
 * We added this as an interface so you can extend all other kinds of menus
 *
 */
public interface MenuQuantitable {

	/**
	 * Get the current quantity of editing
	 *
	 * @return the quantity edit
	 */
	public UIQuantity getQuantity();

	/**
	 * Set a new quantity of editing
	 *
	 * @param newQuantity the new quantity
	 */
	public void setQuantity(UIQuantity newQuantity);

	/**
	 * Get the next edit quantity from click
	 *
	 * @param clickType the click type
	 * @return the next quantity (higher or lower depending on the click)
	 */
	public default int getNextQuantity(ClickType clickType) {
		return clickType == ClickType.LEFT ? - + getQuantity().getAmount() : getQuantity().getAmount();
	}

	/**
	 * Get the button that is responsible for setting the quantity edit
	 * Implemented by default.
	 *
	 * @param menu the menu
	 * @return the button that is responsible for setting the quantity edit
	 */
	public default Button getEditQuantityButton(MenuStandard menu) {
		return new Button() {

			@Override
			public final void onClickedInMenu(Player pl, Menu clickedMenu, ClickType click) {
				setQuantity(click == ClickType.LEFT ? getQuantity().previous() : getQuantity().next());
				menu.redraw();

				menu.animateTitle("&9Editing quantity set to " + getQuantity().getAmount());
			}

			@Override
			public ItemStack getItem() {
				return ItemCreator
						.of(Material.STRING,
								"Edit Quantity: &7" + getQuantity().getAmount(),
								"",
								"&8< &7Left click to decrease",
								"&8> &7Right click to increase")
						.build().make();
			}
		};
	}
}
