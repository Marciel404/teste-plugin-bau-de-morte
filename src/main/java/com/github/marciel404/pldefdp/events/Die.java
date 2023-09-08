package com.github.marciel404.pldefdp.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Die implements Listener {
    int itemdup;
    Location local;
    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event) {

        Objects.requireNonNull(event.getEntity().getPlayer())
                .sendRawMessage(
                        String.format(
                                "As cordenadas da sua ultima morte são x:%s y:%s z:%s",
                                (int) Objects.requireNonNull(
                                        event.getEntity().getPlayer()
                                ).getLocation().getX(),
                                (int) Objects.requireNonNull(
                                        event.getEntity().getPlayer()
                                ).getLocation().getY(),
                                (int) Objects.requireNonNull(
                                        event.getEntity().getPlayer()
                                ).getLocation().getZ()
                        )
                );

        this.local = event.getEntity()
                .getPlayer()
                .getLocation();

        this.local
                .getBlock()
                .setBlockData(
                        Material
                                .CHEST
                                .createBlockData()
                );

        int y = event.getDrops().toArray().length;
        if (y > 27) {
            this.local.add(1,0,0)
                    .getBlock()
                    .setBlockData(
                            Material
                                    .CHEST
                                    .createBlockData()
                    );

        }
        Block b = this.local
                .getBlock();

        if (b.getState() instanceof Chest) {
            Chest chest = (Chest) b.getState();
            Inventory inv = chest.getBlockInventory();
            for (int x = 0; x+1 < y; x++) {

                try {

                    if (x > 27) {

                        chest = (Chest) this.local.add(1, 0, 0)
                                .getBlock().getState();
                    }

                    inv.setStorageContents(event.getDrops().toArray(new ItemStack[x]));

                } catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}