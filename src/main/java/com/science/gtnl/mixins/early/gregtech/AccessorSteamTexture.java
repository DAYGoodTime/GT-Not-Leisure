package com.science.gtnl.mixins.early.gregtech;

import com.cleanroommc.modularui.drawable.UITexture;
import gregtech.common.modularui2.util.SteamTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = SteamTexture.class, remap = false)
public interface AccessorSteamTexture {

    @Invoker("<init>")
    static SteamTexture create(UITexture bronze, UITexture steel, UITexture primitive) {
        throw new AssertionError();
    }
}
