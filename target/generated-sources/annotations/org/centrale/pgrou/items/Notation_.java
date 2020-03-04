package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Test;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Notation.class)
public class Notation_ { 

    public static volatile SingularAttribute<Notation, Integer> notationid;
    public static volatile CollectionAttribute<Notation, Test> testCollection;
    public static volatile SingularAttribute<Notation, String> libelle;

}