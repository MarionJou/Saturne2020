package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Qcmrep;
import org.centrale.pgrou.items.Question;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Image.class)
public class Image_ { 

    public static volatile SingularAttribute<Image, String> nomimage;
    public static volatile CollectionAttribute<Image, Question> questionCollection;
    public static volatile CollectionAttribute<Image, Qcmrep> qcmrepCollection;
    public static volatile SingularAttribute<Image, Integer> imageid;

}