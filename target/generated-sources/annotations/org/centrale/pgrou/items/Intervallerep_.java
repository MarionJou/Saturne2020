package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Intervallerepeval;
import org.centrale.pgrou.items.Reponse;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Intervallerep.class)
public class Intervallerep_ { 

    public static volatile SingularAttribute<Intervallerep, Float> valeur;
    public static volatile SingularAttribute<Intervallerep, Integer> intervallerepid;
    public static volatile CollectionAttribute<Intervallerep, Intervallerepeval> intervallerepevalCollection;
    public static volatile SingularAttribute<Intervallerep, Float> delta;
    public static volatile SingularAttribute<Intervallerep, Reponse> reponseid;

}