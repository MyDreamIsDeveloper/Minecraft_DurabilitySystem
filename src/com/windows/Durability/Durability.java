package com.windows.Durability;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.windows.API.API;

public class Durability extends JavaPlugin implements Listener {
	
	List<String> lore = new ArrayList<String>();
	HashSet<String> list = new HashSet<String>();
	HashSet<String> list2 = new HashSet<String>();
	API api;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		if (getServer().getPluginManager().getPlugin("API") == null) {
			Bukkit.getConsoleSender().sendMessage("§e[!] API 플러그인이 존재하지 않습니다. 플러그인을 종료합니다.");
			Bukkit.getPluginManager().disablePlugin(this);
		} else {
			Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §a내구도 시스템 활성화");
			api = (API) Bukkit.getPluginManager().getPlugin("API");
		}
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[WINDOWS] §c내구도 시스템 비활성화");
	}
	
	public void onHelmet(Player player, Entity damager, int damage) {
		Player player2 = null;
		if (player instanceof Player) {
			if (damager instanceof Player) {
				player2 = (Player)damager;
			}
		if (player.getEquipment().getHelmet() == null) {
			return;
		}
		if (player.getEquipment().getHelmet().hasItemMeta() && player.getEquipment().getHelmet().getItemMeta().hasLore()) {
			for (int i = 0; i < player.getEquipment().getHelmet().getItemMeta().getLore().size(); i++) {
				lore.add(player.getEquipment().getHelmet().getItemMeta().getLore().get(i));
			}
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).split(" ")[0].equals("§b내구도")) {
					EntityEquipment equip = player.getEquipment();
					if (equip.getHelmet().getTypeId() >= 298 && equip.getHelmet().getTypeId() <= 317) equip.getHelmet().setDurability((short) 0);
					String dura = lore.get(i).split(" ")[1];
					if (Integer.parseInt(dura) >= 1) {
						if (damage <= 0) {
							int n = Integer.parseInt(dura);
							n-=1;
							if (n <= 0) {
								player.getEquipment().setHelmet(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
									player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
					        ItemStack item = player.getEquipment().getHelmet();
					        ItemMeta meta = item.getItemMeta();
					        meta.setLore(lore);
					        item.setItemMeta(meta);
							if (Integer.parseInt(dura) <= 0) {
								player.getEquipment().setHelmet(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.clear();
							return;
						}
						int n = Integer.parseInt(dura);
						n-=damage;
						if (n <= 0) {
							player.getEquipment().setHelmet(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
						ItemStack item = player.getEquipment().getHelmet();
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
						if (Integer.parseInt(dura) <= 0) {
							player.getEquipment().setHelmet(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.clear();
						return;
					}
					if (Integer.parseInt(dura) <= 0) {
						player.getEquipment().setHelmet(null);
						player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						if (player2 != null) {
							player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						}
						lore.clear();
						return;
					}
					lore.clear();
					return;
				}
			}
			lore.clear();
			return;
		}
		lore.clear();
		return;
		}
		lore.clear();
		return;
	}
	
	public void onChestplate(Player player, Entity damager, int damage) {
		Player player2 = null;
		if (player instanceof Player) {
			if (damager instanceof Player) {
				player2 = (Player)damager;
			}
		if (player.getEquipment().getChestplate() == null) {
			return;
		}
		if (player.getEquipment().getChestplate().hasItemMeta() && player.getEquipment().getChestplate().getItemMeta().hasLore()) {
			for (int i = 0; i < player.getEquipment().getChestplate().getItemMeta().getLore().size(); i++) {
				lore.add(player.getEquipment().getChestplate().getItemMeta().getLore().get(i));
			}
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).split(" ")[0].equals("§b내구도")) {
					EntityEquipment equip = player.getEquipment();
					if (equip.getChestplate().getTypeId() >= 298 && equip.getChestplate().getTypeId() <= 317) equip.getChestplate().setDurability((short) 0);
					String dura = lore.get(i).split(" ")[1];
					if (Integer.parseInt(dura) >= 1) {
						if (damage <= 0) {
							int n = Integer.parseInt(dura);
							n-=1;
							if (n <= 0) {
								player.getEquipment().setChestplate(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
									player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
					        ItemStack item = player.getEquipment().getChestplate();
					        ItemMeta meta = item.getItemMeta();
					        meta.setLore(lore);
					        item.setItemMeta(meta);
							if (Integer.parseInt(dura) <= 0) {
								player.getEquipment().setChestplate(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.clear();
							return;
						}
						int n = Integer.parseInt(dura);
						n-=damage;
						if (n <= 0) {
							player.getEquipment().setChestplate(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
						ItemStack item = player.getEquipment().getChestplate();
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
						if (Integer.parseInt(dura) <= 0) {
							player.getEquipment().setChestplate(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.clear();
						return;
					}
					if (Integer.parseInt(dura) <= 0) {
						player.getEquipment().setChestplate(null);
						player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						if (player2 != null) {
							player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						}
						lore.clear();
						return;
					}
					lore.clear();
					return;
				}
			}
			lore.clear();
			return;
		}
		lore.clear();
		return;
		}
		lore.clear();
		return;
	}
	
	public void onLeggings(Player player, Entity damager, int damage) {
		Player player2 = null;
		if (player instanceof Player) {
			if (damager instanceof Player) {
				player2 = (Player)damager;
			}
		if (player.getEquipment().getLeggings() == null) {
			return;
		}
		if (player.getEquipment().getLeggings().hasItemMeta() && player.getEquipment().getLeggings().getItemMeta().hasLore()) {
			for (int i = 0; i < player.getEquipment().getLeggings().getItemMeta().getLore().size(); i++) {
				lore.add(player.getEquipment().getLeggings().getItemMeta().getLore().get(i));
			}
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).split(" ")[0].equals("§b내구도")) {
					EntityEquipment equip = player.getEquipment();
					if (equip.getLeggings().getTypeId() >= 298 && equip.getLeggings().getTypeId() <= 317) equip.getLeggings().setDurability((short) 0);
					String dura = lore.get(i).split(" ")[1];
					if (Integer.parseInt(dura) >= 1) {
						if (damage <= 0) {
							int n = Integer.parseInt(dura);
							n-=1;
							if (n <= 0) {
								player.getEquipment().setLeggings(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
									player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
					        ItemStack item = player.getEquipment().getLeggings();
					        ItemMeta meta = item.getItemMeta();
					        meta.setLore(lore);
					        item.setItemMeta(meta);
							if (Integer.parseInt(dura) <= 0) {
								player.getEquipment().setLeggings(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.clear();
							return;
						}
						int n = Integer.parseInt(dura);
						n-=damage;
						if (n <= 0) {
							player.getEquipment().setLeggings(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
						ItemStack item = player.getEquipment().getLeggings();
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
						if (Integer.parseInt(dura) <= 0) {
							player.getEquipment().setLeggings(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.clear();
						return;
					}
					if (Integer.parseInt(dura) <= 0) {
						player.getEquipment().setLeggings(null);
						player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						if (player2 != null) {
							player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						}
						lore.clear();
						return;
					}
					lore.clear();
					return;
				}
			}
			lore.clear();
			return;
		}
		lore.clear();
		return;
		}
		lore.clear();
		return;
	}
	
	public void onBoots(Player player, Entity damager, int damage) {
		Player player2 = null;
		if (player instanceof Player) {
			if (damager instanceof Player) {
				player2 = (Player)damager;
			}
		if (player.getEquipment().getBoots() == null) {
			return;
		}
		if (player.getEquipment().getBoots().hasItemMeta() && player.getEquipment().getBoots().getItemMeta().hasLore()) {
			for (int i = 0; i < player.getEquipment().getBoots().getItemMeta().getLore().size(); i++) {
				lore.add(player.getEquipment().getBoots().getItemMeta().getLore().get(i));
			}
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).split(" ")[0].equals("§b내구도")) {
					EntityEquipment equip = player.getEquipment();
					if (equip.getBoots().getTypeId() >= 298 && equip.getBoots().getTypeId() <= 317) equip.getBoots().setDurability((short) 0);
					String dura = lore.get(i).split(" ")[1];
					if (Integer.parseInt(dura) >= 1) {
						if (damage <= 0) {
							int n = Integer.parseInt(dura);
							n-=1;
							if (n <= 0) {
								player.getEquipment().setBoots(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
									player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
					        ItemStack item = player.getEquipment().getBoots();
					        ItemMeta meta = item.getItemMeta();
					        meta.setLore(lore);
					        item.setItemMeta(meta);
							if (Integer.parseInt(dura) <= 0) {
								player.getEquipment().setBoots(null);
								player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								lore.clear();
								return;
							}
							lore.clear();
							return;
						}
						int n = Integer.parseInt(dura);
						n-=damage;
						if (n <= 0) {
							player.getEquipment().setBoots(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
						ItemStack item = player.getEquipment().getBoots();
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
						if (Integer.parseInt(dura) <= 0) {
							player.getEquipment().setBoots(null);
							player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							if (player2 != null) {
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
							lore.clear();
							return;
						}
						lore.clear();
						return;
					}
					if (Integer.parseInt(dura) <= 0) {
						player.getEquipment().setBoots(null);
						player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						if (player2 != null) {
							player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						}
						lore.clear();
						return;
					}
					lore.clear();
					return;
				}
			}
			lore.clear();
			return;
		}
		lore.clear();
		return;
		}
		lore.clear();
		return;
	}
	
	public void onWeapon(Entity player, Entity damager, int damage) {
		if (damager instanceof Player) {
			Player player2 = (Player)damager;
			player = null;
			if (player instanceof Player) {
				player = (Player)player;
			}
		if (player2.getItemInHand() == null) {
			return;
		}
		if (player2.getItemInHand().hasItemMeta() && player2.getItemInHand().getItemMeta().hasLore()) {
			for (int i = 0; i < player2.getItemInHand().getItemMeta().getLore().size(); i++) {
				lore.add(player2.getItemInHand().getItemMeta().getLore().get(i));
			}
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).split(" ")[0].equals("§b내구도")) {
					EntityEquipment equip = player2.getEquipment();
					if (equip.getItemInHand().getTypeId() == 267 || equip.getItemInHand().getTypeId() == 268 ||
							equip.getItemInHand().getTypeId() == 272 || equip.getItemInHand().getTypeId() == 276 ||
							equip.getItemInHand().getTypeId() == 283) equip.getItemInHand().setDurability((short) 0);
					String dura = lore.get(i).split(" ")[1];
					if (Integer.parseInt(dura) >= 1) {
						if (damage <= 0) {
							int n = Integer.parseInt(dura);
							n-=1;
							if (n <= 0) {
								player2.getInventory().remove(player2.getItemInHand());
								if (player != null) {
									((Player) player).playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								lore.clear();
								return;
							}
							lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
					        ItemStack item = player2.getItemInHand();
					        ItemMeta meta = item.getItemMeta();
					        meta.setLore(lore);
					        item.setItemMeta(meta);
							if (Integer.parseInt(dura) <= 0) {
								player2.getInventory().remove(player2.getItemInHand());
								if (player != null) {
									((Player) player).playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								}
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
								lore.clear();
								return;
							}
							lore.clear();
							return;
						}
						int n = Integer.parseInt(dura);
						n-=damage;
						if (n <= 0) {
							player2.getInventory().remove(player2.getItemInHand());
							if (player != null) {
								((Player) player).playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							lore.clear();
							return;
						}
						lore.set(i, "§b내구도 " + n + " / " + lore.get(i).split(" ")[3]);
						ItemStack item = player2.getItemInHand();
				        ItemMeta meta = item.getItemMeta();
				        meta.setLore(lore);
				        item.setItemMeta(meta);
						if (Integer.parseInt(dura) <= 0) {
							player2.getInventory().remove(player2.getItemInHand());
							if (player != null) {
								((Player) player).playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							}
								player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
							lore.clear();
							return;
						}
						lore.clear();
						return;
					}
					if (Integer.parseInt(dura) <= 0) {
						player2.getInventory().remove(player2.getItemInHand());
						if (player != null) {
							((Player) player).playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						}
							player2.playSound(player2.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
						lore.clear();
						return;
					}
					lore.clear();
					return;
				}
			}
			lore.clear();
			return;
		}
		lore.clear();
		return;
		}
		lore.clear();
		return;
	}
	
	public void run1(EntityDamageByEntityEvent event) {
		if (!event.isCancelled()) {
			if (event.getDamager() instanceof Player) {
			final Player player = (Player)event.getDamager();
			if (list2.contains(player.getName())) return;
			onWeapon(event.getEntity(), event.getDamager(), event.getDamage());
			list2.add(player.getName());
			lore.clear();
			new BukkitRunnable(){
				Integer i = 1;
				@Override
				public void run(){
					if (i == 0){
						list2.remove(player.getName());
						cancel();
						return;
					}
					i--;
				}
			}.runTaskTimer(this,0,10);
			return;
			}
		}
	}
	
	public void run2(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
		final Player player = (Player)event.getEntity();
		if (list.contains(player.getName())) return;
		onHelmet(player, event.getDamager(), event.getDamage());
		onChestplate(player, event.getDamager(), event.getDamage());
		onLeggings(player, event.getDamager(), event.getDamage());
		onBoots(player, event.getDamager(), event.getDamage());
		list.add(player.getName());
		lore.clear();
		new BukkitRunnable(){
			Integer i = 1;
			@Override
			public void run(){
				if (i == 0){
					list.remove(player.getName());
					cancel();
					return;
				}
				i--;
			}
		}.runTaskTimer(this,0,10);
		return;
	}
	}
		
		
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		/*if (!event.isCancelled()) {
		if (event.getDamager() instanceof Player) {
		final Player player = (Player)event.getDamager();
		if (list2.contains(player.getName())) return;
		onWeapon(event.getEntity(), event.getDamager(), event.getDamage());
		list2.add(player.getName());
		lore.clear();
		new BukkitRunnable(){
			Integer i = 1;
			@Override
			public void run(){
				if (i == 0){
					list2.remove(player.getName());
					cancel();
					return;
				}
				i--;
			}
		}.runTaskTimer(this,0,10);
		return;
		}
		if (event.getEntity() instanceof Player) {
		final Player player = (Player)event.getEntity();
		if (list.contains(player.getName())) return;
		onHelmet(player, event.getDamager(), event.getDamage());
		onChestplate(player, event.getDamager(), event.getDamage());
		onLeggings(player, event.getDamager(), event.getDamage());
		onBoots(player, event.getDamager(), event.getDamage());
		list.add(player.getName());
		lore.clear();
		new BukkitRunnable(){
			Integer i = 1;
			@Override
			public void run(){
				if (i == 0){
					list.remove(player.getName());
					cancel();
					return;
				}
				i--;
			}
		}.runTaskTimer(this,0,10);
		return;
	}
	}
	}*/
		run1(event);
		run2(event);
	}
}
