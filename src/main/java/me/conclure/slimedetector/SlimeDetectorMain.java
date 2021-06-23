package me.conclure.slimedetector;

import net.minecraftforge.fml.common.Mod;

import static me.conclure.slimedetector.SlimeDetectorMain.MOD_ID;

@SuppressWarnings("ClassInitializerMayBeStatic")
@Mod(MOD_ID)
public final class SlimeDetectorMain {
    public static final String MOD_ID = "slimedetector";

    {
        ItemRegistry.register();
    }
}
