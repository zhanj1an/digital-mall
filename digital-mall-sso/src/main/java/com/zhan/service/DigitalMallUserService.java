package com.zhan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DigitalMallUserService {

    int consoleLogin(String username, String password, HttpServletResponse response, HttpServletRequest request);

    int portalLogin(String username, String password, HttpServletResponse response, HttpServletRequest request);
}
