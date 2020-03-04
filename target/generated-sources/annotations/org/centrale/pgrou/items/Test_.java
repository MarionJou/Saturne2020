package org.centrale.pgrou.items;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Evaluation;
import org.centrale.pgrou.items.Groupe;
import org.centrale.pgrou.items.Notation;
import org.centrale.pgrou.items.Quiz;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Test.class)
public class Test_ { 

    public static volatile SingularAttribute<Test, Date> datefintest;
    public static volatile SingularAttribute<Test, Notation> notationid;
    public static volatile SingularAttribute<Test, Quiz> quizid;
    public static volatile SingularAttribute<Test, Date> dureemaxtest;
    public static volatile SingularAttribute<Test, Date> datedebuttest;
    public static volatile SingularAttribute<Test, Integer> testid;
    public static volatile CollectionAttribute<Test, Evaluation> evaluationCollection;
    public static volatile SingularAttribute<Test, Groupe> groupeid;

}