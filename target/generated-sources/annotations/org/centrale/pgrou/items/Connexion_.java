package org.centrale.pgrou.items;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Personne;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Connexion.class)
public class Connexion_ { 

    public static volatile SingularAttribute<Connexion, String> ip;
    public static volatile SingularAttribute<Connexion, Personne> personneid;
    public static volatile SingularAttribute<Connexion, Date> finconnexion;
    public static volatile SingularAttribute<Connexion, String> connexionid;
    public static volatile SingularAttribute<Connexion, Date> debutconnexion;

}