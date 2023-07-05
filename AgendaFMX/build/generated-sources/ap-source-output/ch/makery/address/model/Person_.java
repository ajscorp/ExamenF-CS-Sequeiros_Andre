package ch.makery.address.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-07-05T15:15:17", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, ObjectProperty> birthday;
    public static volatile SingularAttribute<Person, StringProperty> firstName;
    public static volatile SingularAttribute<Person, StringProperty> lastName;
    public static volatile SingularAttribute<Person, StringProperty> city;
    public static volatile SingularAttribute<Person, StringProperty> street;
    public static volatile SingularAttribute<Person, IntegerProperty> postalCode;
    public static volatile SingularAttribute<Person, Integer> personId;

}