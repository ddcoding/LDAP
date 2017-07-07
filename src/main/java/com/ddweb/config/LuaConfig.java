package com.ddweb.config;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
     *  @return True if lua is loaded propably from entered PATH , false if not.
     */
    public boolean LuaMainInit()
    {
        File f = new File(LuaConfig.LUA_PATH);
        if(f.exists() && !f.isDirectory()) {
            Globals globals = JsePlatform.standardGlobals();
            LuaValue chunk = globals.loadfile(LuaConfig.LUA_PATH);
            chunk.call();
            return true;
        }
        else {
            System.err.println("PATH TO LUA FILE IS WRONG!");
            return false;
        }
    }
}
