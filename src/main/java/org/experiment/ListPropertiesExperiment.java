package org.experiment;

import io.vavr.collection.List;
import lombok.Getter;
import lombok.Setter;
import org.experiment.entity.Mother;
import org.experiment.entity.Parent;

@Getter
@Setter
public class ListPropertiesExperiment {
    private List<Parent> vavrList = List.of(new Mother());
}
