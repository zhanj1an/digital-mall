package com.zhan.sso.service;

import com.zhan.model.DigitalMallUser;

public interface DigitalMallUserService {

    DigitalMallUser selectAdminUser(DigitalMallUser user);

    DigitalMallUser selectGeneralUser(DigitalMallUser user);
}
