package com.ddweb.service.interpreterTests;

import com.ddweb.service.Interpreter;
import com.ddweb.structures.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test behaviour interpret method.
 * @see Interpreter
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InterpretTest {

    private static final String GROUP_ADMIN = "ou=admin";

    private static final String GROUP_SUPPORT = "ou=Support";

    private Pair<String,String> correctPair;

    @Autowired
    private Interpreter interpreter;

    @Autowired
    private Environment env;

    @Before
    public void fillPair()
    {
        correctPair = new Pair<>("ou",InterpretTest.GROUP_ADMIN + "," + InterpretTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
    }

    /**
     *  check behaviour with correct data
     */
    @Test
    public void interpSuccess()
    {
        assertThat(interpreter.interpret("admin")).isEqualTo(correctPair);
    }

    /**
     *  check behaviour with wrong data
     */
    @Test
    public void interpFailure(){assertThat(interpreter.interpret("value")).isNotEqualTo(correctPair);}

    /**
     *  check behaviour with null data
     */
    @Test
    public void interpNull() {assertThat(interpreter.interpret(null)).isNull();}
    /**
     *  check behaviour without data
     */
    @Test
    public void interpEmpty() {assertThat(interpreter.interpret("")).isNull();}
}
