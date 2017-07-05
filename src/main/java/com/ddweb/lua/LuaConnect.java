package com.ddweb.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.springframework.stereotype.Component;

@Component
public class LuaConnect {

    public void ExamplePrint()
    {
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.loadfile("src/main/resources/hello.lua");
        chunk.call();
    }
}
