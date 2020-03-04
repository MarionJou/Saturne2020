package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Personne;
import org.centrale.pgrou.items.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Groupe.class)
public class Groupe_ { 

    public static volatile SingularAttribute<Groupe, Boolean> estvalide;
    public static volatile CollectionAttribute<Groupe, Personne> personneCollection;
    public static volatile CollectionAttribute<Groupe, Test> testCollection;
    public static volatile SingularAttribute<Groupe, String> nomgroupe;
    public static volatile SingularAttribute<Groupe, Integer> groupeid;

}