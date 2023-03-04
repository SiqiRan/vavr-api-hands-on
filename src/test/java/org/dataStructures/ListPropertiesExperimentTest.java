package org.dataStructures;

import io.vavr.collection.List;
import org.apache.log4j.Logger;
import org.experiment.ListPropertiesExperiment;
import org.experiment.entity.Father;
import org.experiment.entity.Mother;
import org.experiment.entity.Parent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

class ListPropertiesExperimentTest {
    public static Logger logger = Logger.getLogger(ListPropertiesExperimentTest.class);

    @Test
    void testTypeSafety() {
        ListPropertiesExperiment listPropertiesExperiment = new ListPropertiesExperiment();
        try {
            List<Parent> vavrList = listPropertiesExperiment.getVavrList();
            logger.info(vavrList.getClass().getInterfaces());
            vavrList = vavrList.append(new Mother());
            vavrList = vavrList.append(new Father());
            logger.info(vavrList.combinations());
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
            fail();
        } catch (ArrayStoreException ignored) {

        }

        //This line of code won't work (invariance)
        //ArrayList<Parent> arrayList = new ArrayList<Mother>();
    }

}