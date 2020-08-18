package dev.ichigo.ffa.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerListener implements Listener {
	
	@EventHandler
	public void WeatherChange(final WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	
	public void ExploitTnt(final ExplosionPrimeEvent event) {
		event.setCancelled(true);
	}

}
