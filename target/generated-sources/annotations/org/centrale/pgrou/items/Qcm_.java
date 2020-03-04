package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Qcm.class)
public class Qcm_ { 

    public static volatile SingularAttribute<Qcm, Integer> qcmid;
    public static volatile SingularAttribute<Qcm, Question> questionid;
    public static volatile SingularAttribute<Qcm, Boolean> repunique;

}