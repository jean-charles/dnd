package com.gayasystem.games.dnd.lifeforms;

import com.gayasystem.games.dnd.lifeforms.body.organs.brain.memories.emotions.Emotion;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Hair;
import com.gayasystem.games.dnd.lifeforms.body.organs.sensitives.Organ;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest(classes = {})
public class OrgansAssemblyTest {
    class ALifeForm extends LifeForm {
        private static final Collection<Organ> theOrgans = new ArrayList<>();

        {
            theOrgans.add(new Hair());
        }

        public ALifeForm() {
            super(1, 1, 1, Gender.female, 1, 1, 1, null, 1, Emotion.neutral, null, theOrgans);
        }
    }
}
