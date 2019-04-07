package org.mineacademy.designer.model;

import org.mineacademy.designer.menu.impl.MenuQuantitable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents how much we should change an
 * ItemStack's size upon clicking the item in the menu.
 *
 * For example use, see {@link MenuQuantitable}
 */
@RequiredArgsConstructor
@Getter
public enum UIQuantity {

	/**
	 * Change ItemStack size by 1
	 */
	ONE(1),

	/**
	 * Change ItemStack size by 2
	 */
	TWO(2),

	/**
	 * Change ItemStack size by 5
	 */
	FIVE(5),

	/**
	 * Change ItemStack size by 10
	 */
	TEN(10),

	/**
	 * Change ItemStack size by 20
	 */
	TWENTY(20);

	/**
	 * The amount to change
	 */
	private final int amount;

	/**
	 * Rotates the enum backwards
	 *
	 * @return the previous enum ordinal, or last if overflows
	 */
	public final UIQuantity previous() {
		final int next = ordinal() - 1;
		final UIQuantity[] values = UIQuantity.values();

		return next >= 0 ? values[next] : values[values.length - 1];
	}

	/**
	 * Rotates the enum forward
	 *
	 * @return the next enum ordinal, or first if overflows
	 */
	public final UIQuantity next() {
		final int next = ordinal() + 1;
		final UIQuantity[] values = UIQuantity.values();

		return next >= values.length ? values[0] : values[next];
	}
}