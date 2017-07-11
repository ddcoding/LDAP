package com.ddweb.service.interpreterTests;

import com.ddweb.service.Interpreter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test behaviour merge method.
 * @see Interpreter
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MergeTest {

    @Autowired
    private Interpreter interpreter;

    @Autowired
    private Environment env;

    private List<String> stringList;

    private List<String> stringWrongList;

    private static final String GROUP_ADMIN = "ou=admin";

    private static final String GROUP_USER = "ou=user";

    private static final String GROUP_MOD = "ou=mod";

    private static final String GROUP_SUPPORT = "ou=Support";

    @Before
    public void fillList()
    {
        stringList = new ArrayList<>();
        stringList.add("admin");
        stringList.add("user");
        stringList.add("mod");

        stringWrongList = new ArrayList<>();
        stringWrongList.add("adminddd");
        stringWrongList.add("moddd");
        stringWrongList.add("userrr");
    }

    /**
     *  check behaviour with correct data
     */
    @Test
    public void mergeSuccess()
    {
        assertThat(interpreter.merge(stringList))
                .contains("ou")
                .contains(MergeTest.GROUP_ADMIN + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"))
                .contains(MergeTest.GROUP_USER + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"))
                .contains(MergeTest.GROUP_MOD + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
    }

    /**
     * check behaviour with wrong data
     */
    @Test
    public void mergeFailure()
    {
        assertThat(interpreter.merge(stringWrongList))
                .doesNotContain("ou")
                .doesNotContain(MergeTest.GROUP_ADMIN + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"))
                .doesNotContain(MergeTest.GROUP_USER + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"))
                .doesNotContain(MergeTest.GROUP_MOD + "," + MergeTest.GROUP_SUPPORT + "," + env.getProperty("LdapBase"));
    }

    /**
     *  check behaviour without records
     */
    @Test
    public void mergeEmpty()
    {
        assertThat(interpreter.merge( new ArrayList<>()).isEmpty());
    }

    /**
     *  check behaviour while receiving null
     */
    @Test
    public void mergeNull()
    {
        assertThat(interpreter.merge(null)).isNull();
    }

}
