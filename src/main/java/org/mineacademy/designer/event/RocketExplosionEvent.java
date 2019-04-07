package org.mineacademy.designer.event;

import org.bukkit.Location;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.mineacademy.designer.tool.impl.Rocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The event that is triggered when a {@link Rocket} explodes.
 */
@RequiredArgsConstructor
@Getter
public final class RocketExplosionEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	/**
	 * The rocket
	 */
	private final Rocket rocket;

	/**
	 * The projectile
	 */
	private final Projectile projectile;

	/**
	 * The explosion location
	 */
	private final Location location;

	/**
	 * Is the event cancelled?
	 */
	@Setter
	private boolean cancelled;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}