package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Evaluationquestion;
import org.centrale.pgrou.items.Intervallerepeval;
import org.centrale.pgrou.items.Qcmrepeval;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Evaluationreponse.class)
public class Evaluationreponse_ { 

    public static volatile SingularAttribute<Evaluationreponse, Evaluationquestion> evaluationquestion;
    public static volatile CollectionAttribute<Evaluationreponse, Qcmrepeval> qcmrepevalCollection;
    public static volatile SingularAttribute<Evaluationreponse, Boolean> juste;
    public static volatile CollectionAttribute<Evaluationreponse, Intervallerepeval> intervallerepevalCollection;
    public static volatile SingularAttribute<Evaluationreponse, Integer> evaluationreponseid;

}