package com.ddweb.service;

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
 * Test good behaviour interpreter.
 * @see Interpreter
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InterpreterTests {

    @Autowired
    private Interpreter interpreter;

    private List<String> stringList;

    @Before
    public void fillList()
    {
        stringList = new ArrayList<>();
        stringList.add("admin");
        stringList.add("user");
        stringList.add("mod");
    }

    @Test
    public void interpWorks()
    {
        assertThat(interpreter.interpret("admin")).isEqualTo("ou");
    }

    @Test
    public void interpMergeWorks()
    {
        assertThat(interpreter.merge(stringList))
                .contains("ou")
                .contains("admin")
                .contains("user")
                .contains("mod");
    }
}
