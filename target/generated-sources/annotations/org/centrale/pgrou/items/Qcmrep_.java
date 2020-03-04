package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Image;
import org.centrale.pgrou.items.Qcmrepeval;
import org.centrale.pgrou.items.Reponse;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Qcmrep.class)
public class Qcmrep_ { 

    public static volatile SingularAttribute<Qcmrep, String> enonce;
    public static volatile SingularAttribute<Qcmrep, Image> imageid;
    public static volatile CollectionAttribute<Qcmrep, Qcmrepeval> qcmrepevalCollection;
    public static volatile SingularAttribute<Qcmrep, Integer> qcmrepid;
    public static volatile SingularAttribute<Qcmrep, Reponse> reponseid;

}