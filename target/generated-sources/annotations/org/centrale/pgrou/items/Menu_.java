package org.centrale.pgrou.items;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.centrale.pgrou.items.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-03T15:15:44")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile CollectionAttribute<Menu, Role> roleCollection;
    public static volatile SingularAttribute<Menu, Integer> menuid;
    public static volatile SingularAttribute<Menu, String> nom;

}