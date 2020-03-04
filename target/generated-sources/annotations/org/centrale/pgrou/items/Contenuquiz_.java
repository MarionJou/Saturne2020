package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Evaluationquestion;
import org.centrale.pgrou.items.Question;
import org.centrale.pgrou.items.Quiz;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Contenuquiz.class)
public class Contenuquiz_ { 

    public static volatile SingularAttribute<Contenuquiz, Integer> nombrepoints;
    public static volatile SingularAttribute<Contenuquiz, Question> questionid;
    public static volatile SingularAttribute<Contenuquiz, Integer> contenuquizid;
    public static volatile SingularAttribute<Contenuquiz, Quiz> quizid;
    public static volatile CollectionAttribute<Contenuquiz, Evaluationquestion> evaluationquestionCollection;

}