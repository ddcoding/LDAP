package com.ddweb.service.interpreterTests;

import com.ddweb.service.Interpreter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private Interpreter interpreter;

    /**
     *  check behaviour with correct data
     */
    @Test
    public void interpSuccess()
    {
        assertThat(interpreter.interpret("admin")).isEqualTo("ou");
    }

    /**
     *  check behaviour with wrong data
     */
    @Test
    public void interpFailure(){assertThat(interpreter.interpret("value")).isNotEqualTo("ou");}

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
