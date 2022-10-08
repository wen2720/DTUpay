// // Testing for JPA entity with hibernate UUID generator, require to extend JpaRepository with CustomerIdRepository
// package dtustudent.dtupay.model;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Column;

// import org.hibernate.annotations.GenericGenerator;

// // Class tagged with entity would take effect of creating tables in the database
// @Entity 
// public class CustomerId {
//     // SQL primary key, hibernate UUID generator
//     @Id 
//     @GeneratedValue(generator = "uuid2")
//     @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//     private String id;

//     public CustomerId(){}   //new CustomerId(); creates SQL table if not exist and insert row.
//     public String getId() {
//         return this.id;
//     }
// }