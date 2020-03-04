package org.centrale.pgrou.items;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Evaluationquestion;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Evaluation.class)
public class Evaluation_ { 

    public static volatile SingularAttribute<Evaluation, Integer> evaluationid;
    public static volatile SingularAttribute<Evaluation, Float> note;
    public static volatile SingularAttribute<Evaluation, Date> dureeevaluation;
    public static volatile SingularAttribute<Evaluation, Date> datedebutevaluation;
    public static volatile SingularAttribute<Evaluation, Personne> personneid;
    public static volatile SingularAttribute<Evaluation, Test> testid;
    public static volatile CollectionAttribute<Evaluation, Evaluationquestion> evaluationquestionCollection;

}