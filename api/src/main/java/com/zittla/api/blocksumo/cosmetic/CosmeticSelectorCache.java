package com.zittla.api.blocksumo.cosmetic;

import com.zittla.api.blocksumo.cosmetic.types.IAuraCosmetic;
import com.zittla.api.blocksumo.cosmetic.types.IDeathCryCosmetic;
import com.zittla.api.blocksumo.cosmetic.types.IKillMessageCosmetic;
import com.zittla.api.blocksumo.cosmetic.types.IPlatformCosmetic;
import com.zittla.api.blocksumo.cosmetic.types.ITauntCosmetic;
import com.zittla.api.blocksumo.cosmetic.types.IVictoryDance;

public interface CosmeticSelectorCache {

  CosmeticSelector<IAuraCosmetic> getAuraSelector();

  CosmeticSelector<IDeathCryCosmetic> getDeathCrySelector();

  CosmeticSelector<IKillMessageCosmetic> getKillMessageSelector();

  CosmeticSelector<IPlatformCosmetic> getPlatformSelector();

  CosmeticSelector<ITauntCosmetic> getTauntSelector();

  CosmeticSelector<IVictoryDance> getVictoryDanceSelector();

}
