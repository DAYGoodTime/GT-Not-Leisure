package com.science.gtnl.mixins.late.gregtech;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import gregtech.common.tileentities.machines.MTEHatchInputBusME;

@Mixin(value = MTEHatchInputBusME.class, remap = false)
public interface AccessorMTEHatchInputBusME {

    @Accessor("slots")
    MTEHatchInputBusME.Slot[] getSlots();
}
