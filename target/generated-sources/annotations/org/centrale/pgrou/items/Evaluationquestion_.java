package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Evaluation;
import org.centrale.pgrou.items.EvaluationquestionPK;
import org.centrale.pgrou.items.Evaluationreponse;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Evaluationquestion.class)
public class Evaluationquestion_ { 

    public static volatile SingularAttribute<Evaluationquestion, Evaluation> evaluation;
    public static volatile SingularAttribute<Evaluationquestion, EvaluationquestionPK> evaluationquestionPK;
    public static volatile SingularAttribute<Evaluationquestion, Contenuquiz> contenuquiz;
    public static volatile CollectionAttribute<Evaluationquestion, Evaluationreponse> evaluationreponseCollection;
    public static volatile SingularAttribute<Evaluationquestion, Float> notequestion;

}