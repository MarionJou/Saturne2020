package org.centrale.pgrou.items;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Contenuquiz;
import org.centrale.pgrou.items.Image;
import org.centrale.pgrou.items.Intervalle;
import org.centrale.pgrou.items.Motcle;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Qcm;
import org.centrale.pgrou.items.Reponse;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, String> enonce;
    public static volatile SingularAttribute<Question, Integer> questionid;
    public static volatile SingularAttribute<Question, Image> imageid;
    public static volatile SingularAttribute<Question, Boolean> estprivee;
    public static volatile CollectionAttribute<Question, Motcle> motcleCollection;
    public static volatile SingularAttribute<Question, Personne> personneid;
    public static volatile CollectionAttribute<Question, Qcm> qcmCollection;
    public static volatile CollectionAttribute<Question, Reponse> reponseCollection;
    public static volatile SingularAttribute<Question, Date> datecreationquestion;
    public static volatile CollectionAttribute<Question, Contenuquiz> contenuquizCollection;
    public static volatile CollectionAttribute<Question, Intervalle> intervalleCollection;

}