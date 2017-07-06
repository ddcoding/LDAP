package com.ddweb.config;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
/**
 *  Lua Configuration file
 */
@Configuration
public class LuaConfig {
    /**
     *  main.lua path
     */
    private final static String LUA_PATH = "src/main/resources/lua/main.lua";
    /**
     *  Initialization of main.lua
     */
    public void LuaMainInit()
    {
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.loadfile(LuaConfig.LUA_PATH);
        chunk.call();
    }
}
