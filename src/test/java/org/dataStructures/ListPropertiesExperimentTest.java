package org.dataStructures;

import io.vavr.collection.List;
import org.dataStructures.entity.Father;
import org.dataStructures.entity.Mother;
import org.dataStructures.entity.Parent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

class ListPropertiesExperimentTest {

    @Test
    void testTypeSafety() {
        ListPropertiesExperiment listPropertiesExperiment = new ListPropertiesExperiment();
        try {
            List<Parent> vavrList = listPropertiesExperiment.vavrList;
            vavrList = vavrList.append(new Mother());
            vavrList = vavrList.append(new Father());
            System.out.println(vavrList.combinations());
        } catch (Exception e) {
            fail();
        }

        try {
            Parent[] array = new Mother[2];
            array[0] = new Mother();
            array[1] = new Father();
            java.util.List<Mother> mothers = new ArrayList<>();
            //next line of code will trigger compiler error
            //java.util.List<Parent> parents = mothers;
        } catch (ArrayStoreException ignored) {

        }

        //This line of code won't work (invariance)
        //ArrayList<Parent> arrayList = new ArrayList<Mother>();
    }

}