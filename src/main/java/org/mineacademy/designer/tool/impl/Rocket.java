package org.mineacademy.designer.tool.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.player.PlayerInteractEvent;
import org.mineacademy.designer.event.RocketExplosionEvent;
import org.mineacademy.designer.tool.Tool;

public abstract class Rocket extends Tool {

	@Override
	public void onBlockClick(PlayerInteractEvent e) {
	}

	public boolean canLaunch(Player shooter, Location loc) {
		return true;
	}

	public abstract void onLaunch(org.bukkit.entity.Projectile proj, Player shooter);

	public abstract void onImpact(org.bukkit.entity.Projectile proj, Player shooter, Location loc);

	protected final void explode(Projectile proj, Location loc, float power, boolean breakBlocks) {
		final RocketExplosionEvent event = new RocketExplosionEvent(this, proj, loc);
		Bukkit.getPluginManager().callEvent(event);

		if (!event.isCancelled())
			proj.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, false, breakBlocks);
	}
}
