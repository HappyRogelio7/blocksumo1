package com.zittla.api.blocksumo.cosmetic;

import com.zittla.api.blocksumo.model.user.IUser;

public interface CosmeticSelector<T> {

  T select(IUser entity);

}
