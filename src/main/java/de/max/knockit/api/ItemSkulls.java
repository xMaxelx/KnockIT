package de.max.knockit.api;

import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Base64;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */
/*     */
/*     */ public class ItemSkulls
        /*     */ {
    /*     */   private static Class<?> skullMetaClass;
    /*     */   private static Class<?> tileEntityClass;
    /*     */   private static Class<?> blockPositionClass;
    /*     */   private static int mcVersion;
    /*     */
    /*     */   static {
        /*  27 */     String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        /*  28 */     mcVersion = Integer.parseInt(version.replaceAll("[^0-9]", ""));
        /*     */     try {
            /*  30 */       skullMetaClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftMetaSkull");
            /*  31 */       tileEntityClass = Class.forName("net.minecraft.server." + version + ".TileEntitySkull");
            /*  32 */       if (mcVersion > 174) {
                /*  33 */         blockPositionClass = Class.forName("net.minecraft.server." + version + ".BlockPosition");
                /*     */       } else {
                /*  35 */         blockPositionClass = null;
                /*     */       }
            /*  37 */     } catch (ClassNotFoundException e) {
            /*  38 */       e.printStackTrace();
            /*     */     }
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public static ItemStack getSkull(String skinURL) {
        /*  48 */     return getSkull(skinURL, 1);
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public static ItemStack getSkull(String skinURL, int amount) {
        /*  58 */     ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short)3);
        /*  59 */     SkullMeta meta = (SkullMeta)skull.getItemMeta();
        /*     */     try {
            /*  61 */       Field profileField = skullMetaClass.getDeclaredField("profile");
            /*  62 */       profileField.setAccessible(true);
            /*  63 */       profileField.set(meta, getProfile(skinURL));
            /*  64 */     } catch (Exception ex) {
            /*  65 */       ex.printStackTrace();
            /*     */     }
        /*  67 */     skull.setItemMeta((ItemMeta)meta);
        /*  68 */     return skull;
        /*     */   }
    public static boolean setBlock(Location loc, String skinURL) {
        /*  78 */     return setBlock(loc.getBlock(), skinURL);
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   public static boolean setBlock(Block block, String skinURL) {
        /*  88 */     boolean flag = (block.getType() == Material.SKULL);
        /*  89 */     if (!flag) {
            /*  90 */       block.setType(Material.SKULL);
            /*     */     }
        /*     */     try {
            /*  93 */       Object nmsWorld = block.getWorld().getClass().getMethod("getHandle", new Class[0]).invoke(block.getWorld(), new Object[0]);
            /*  94 */       Object tileEntity = null;
            /*     */
            /*  96 */       if (mcVersion <= 174) {
                /*  97 */         Method getTileEntity = nmsWorld.getClass().getMethod("getTileEntity", new Class[] { int.class, int.class, int.class });
                /*  98 */         tileEntity = tileEntityClass.cast(getTileEntity.invoke(nmsWorld, new Object[] { Integer.valueOf(block.getX()), Integer.valueOf(block.getY()), Integer.valueOf(block.getZ()) }));
                /*     */       } else {
                /* 100 */         Method getTileEntity = nmsWorld.getClass().getMethod("getTileEntity", new Class[] { blockPositionClass });
                /* 101 */         tileEntity = tileEntityClass.cast(getTileEntity.invoke(nmsWorld, new Object[] { getBlockPositionFor(block.getX(), block.getY(), block.getZ()) }));
                /*     */       }
            /*     */
            /* 104 */       tileEntityClass.getMethod("setGameProfile", new Class[] { GameProfile.class }).invoke(tileEntity, new Object[] { getProfile(skinURL) });
            /* 105 */     } catch (Exception ex) {
            /* 106 */       ex.printStackTrace();
            /*     */     }
        /* 108 */     return !flag;
        /*     */   }
    /*     */
    /*     */   private static GameProfile getProfile(String skinURL) {
        /* 112 */     GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        /* 113 */     String base64encoded = Base64.getEncoder().encodeToString((new String("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")).getBytes());
        /* 114 */     Property property = new Property("textures", base64encoded);
        /* 115 */     profile.getProperties().put("textures", property);
        /* 116 */     return profile;
        /*     */   }
    /*     */
    /*     */   private static Object getBlockPositionFor(int x, int y, int z) {
        /* 120 */     Object blockPosition = null;
        /*     */     try {
            /* 122 */       Constructor<?> cons = blockPositionClass.getConstructor(new Class[] { int.class, int.class, int.class });
            /* 123 */       blockPosition = cons.newInstance(new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
            /* 124 */     } catch (Exception ex) {
            /* 125 */       ex.printStackTrace();
            /*     */     }
        /* 127 */     return blockPosition;
        /*     */   }
}
