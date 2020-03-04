package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Connexion;
import org.centrale.pgrou.items.Evaluation;
import org.centrale.pgrou.items.Groupe;
import org.centrale.pgrou.items.Question;
import org.centrale.pgrou.items.Quiz;
import org.centrale.pgrou.items.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Personne.class)
public class Personne_ { 

    public static volatile CollectionAttribute<Personne, Question> questionCollection;
    public static volatile CollectionAttribute<Personne, Connexion> connexionCollection;
    public static volatile CollectionAttribute<Personne, Role> roleCollection;
    public static volatile SingularAttribute<Personne, Integer> personneid;
    public static volatile CollectionAttribute<Personne, Groupe> groupeCollection;
    public static volatile CollectionAttribute<Personne, Quiz> quizCollection;
    public static volatile CollectionAttribute<Personne, Evaluation> evaluationCollection;
    public static volatile SingularAttribute<Personne, String> motdepasse;
    public static volatile SingularAttribute<Personne, String> login;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> prenom;

}