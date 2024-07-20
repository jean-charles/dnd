package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.lifeforms.brain.BrainFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {LifeFormTestConfig.class})
class LifeFormTest {
    static final double MASS = 1.2;
    static final double SPEED = 2.3;
    static final Collection<Class<? extends Thing>> SCARED_BY = List.of();
    static final Collection<Class<? extends Thing>> ATTRACTED_BY = List.of();

    @MockBean
    BrainFactory brainFactory;

    @Autowired
    MyBean lifeForm;
//    LifeFormA lifeForm;

    @Test
    void mass() {
        assertThat(brainFactory).isNotNull();
        assertThat(lifeForm).isNotNull();
//        assertEquals(MASS, lifeForm.mass());
    }

//    @Test
//    void run() {
//        // TODO Will be covered when LifeFord will take Brain as a Bean
//    }

//    @Test
//    void see() {
//        // TODO Will be covered when LifeFord will take Brain as a Bean
//    }

//    @Test
//    void ear() {
//        // TODO Will be covered when LifeFord will take Brain as a Bean
//    }

//    @Test
//    void sightDistance() {
//        // TODO Need setter before
//    }
}
