package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Intervallerep;
import org.centrale.pgrou.items.Qcmrep;
import org.centrale.pgrou.items.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Reponse.class)
public class Reponse_ { 

    public static volatile CollectionAttribute<Reponse, Qcmrep> qcmrepCollection;
    public static volatile SingularAttribute<Reponse, Question> questionid;
    public static volatile CollectionAttribute<Reponse, Intervallerep> intervallerepCollection;
    public static volatile SingularAttribute<Reponse, Boolean> correcte;
    public static volatile SingularAttribute<Reponse, Integer> reponseid;

}