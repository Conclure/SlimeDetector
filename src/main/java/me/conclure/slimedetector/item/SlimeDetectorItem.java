package me.conclure.slimedetector.item;

import net.minecraft.data.ModelTextures;
import net.minecraft.data.ModelsResourceUtil;
import net.minecraft.data.StockModelShapes;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import java.util.concurrent.ThreadLocalRandom;

public class SlimeDetectorItem extends Item implements IVanishable {

  public SlimeDetectorItem(Properties properties) {
    super(properties);
    StockModelShapes.FLAT_HANDHELD_ITEM.create(ModelsResourceUtil.getModelLocation(this), ModelTextures.layer0(this),(o0,o1) -> {});
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
    ItemStack itemInHand = player.getItemInHand(hand);

    if (!world.isClientSide && world instanceof ISeedReader) {
      ChunkPos chunkPos = new ChunkPos(player.blockPosition());
      boolean flag = SharedSeedRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, ((ISeedReader)world).getSeed(), 987234911L).nextInt(10) == 0;
      SoundEvent soundEvent;
      float pitch;

      if (flag) {
        soundEvent = SoundEvents.SLIME_SQUISH;
        pitch = 1f;
        player.sendMessage(new TranslationTextComponent("chat.slimeChunkDetected"), Util.NIL_UUID);
      } else {
        pitch = 0.5f;
        soundEvent = SoundEvents.SLIME_SQUISH_SMALL;
      }

      world.playSound(null,player.getX(),player.getY(),player.getZ(),soundEvent, SoundCategory.NEUTRAL, pitch, 0.4F);

      itemInHand.hurtAndBreak(1,player,playerEntity -> playerEntity.broadcastBreakEvent(hand));
    }

    player.getCooldowns().addCooldown(this,40);
    player.awardStat(Stats.ITEM_USED.get(this));

    return ActionResult.sidedSuccess(itemInHand,world.isClientSide);
  }

  @Override
  public int getEnchantmentValue() {
    return 1;
  }
}
