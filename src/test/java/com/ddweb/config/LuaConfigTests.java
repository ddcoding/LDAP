package com.ddweb.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Test class for Lua Configuration
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuaConfigTests {
    public LuaConfigTests() {
    }
    @Autowired
    private LuaConfig luaConfig;

    /**
     * checking if main file exist
     */
    @Test
    public void luaFileConnection()
    {
        assertThat(luaConfig.LuaMainInit()).isTrue();
    }
    /**
     * checking if main file doesn't exist
     */
    @Test
    public void luaFileConnectionFailure()
    {
        assertThat(luaConfig.LuaMainInit()).isFalse();
    }
}
