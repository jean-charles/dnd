package com.gayasystem.games.dnd.lifeforms.organs;

import com.gayasystem.games.dnd.lifeforms.brain.Brain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {Hair.class, Brain.class})
class HairTest {
    @Autowired
    Hair hair;

    @MockBean
    Brain brain;

    @Test
    void press() {
        hair.press(1.0);
//        brain.
    }
}