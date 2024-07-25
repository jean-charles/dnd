package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(classes = {Application.class})
class ApplicationTest {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    Collection<Thing> things;

    @Test
    void test() {
        assertThat(things).isNotNull();
        assertEquals(1, things.size());
        var thing = things.iterator().next();
        assertTrue(thing instanceof ThingA);
    }
}