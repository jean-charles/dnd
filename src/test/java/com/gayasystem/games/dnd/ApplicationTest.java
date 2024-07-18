package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = Application.class)
class ApplicationTest {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    Collection<Thing> things;

    @Test
    void test() {
        String[] beanNames = ctx.getBeanDefinitionNames();
        assertThat(beanNames).isNotNull();
        assertEquals(58, beanNames.length);
        assertThat(things).isNotNull();
        assertEquals(1, things.size());
    }
}