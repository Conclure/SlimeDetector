package me.conclure.slimedetector;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import me.conclure.slimedetector.item.SlimeDetectorItem;

@SuppressWarnings("CodeBlock2Expr")
public class ItemRegistry {
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeDetectorMain.MOD_ID);

  static void register() {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static final RegistryObject<SlimeDetectorItem> SLIME_DETECTOR = ITEMS.register("slime_detector",() -> {
    return new SlimeDetectorItem(new Item.Properties().durability(397).tab(ItemGroup.TAB_TOOLS));
  });
}
