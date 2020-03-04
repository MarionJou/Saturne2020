package org.centrale.pgrou.items;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Quiz.class)
public class Quiz_ { 

    public static volatile SingularAttribute<Quiz, Integer> quizid;
    public static volatile SingularAttribute<Quiz, Date> datecreationquiz;
    public static volatile CollectionAttribute<Quiz, Test> testCollection;
    public static volatile SingularAttribute<Quiz, Personne> personneid;
    public static volatile CollectionAttribute<Quiz, Contenuquiz> contenuquizCollection;
    public static volatile SingularAttribute<Quiz, String> nomquiz;

}