# ReadSearch

![Java JDK](https://img.shields.io/badge/Java_JDK-v17.0-blue)
![IDE](https://img.shields.io/badge/IDE-Intellij_IDEA-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-v3.3.0-blue)
![Maven](https://img.shields.io/badge/Proyect-Maven-blue)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL-v14.12-blue)
![Jackson](https://img.shields.io/badge/jackson-v2.17.2-blue)


Project developed for the ONE-Backend program. This console application consumes the Gutendex API to query books and store them in a PostgreSQL database. It also performs queries on the stored data to retrieve information.
</br></br>



## ⚙️ Application functions
* Consuming the gutendex Api to search for books
* Storing books and authors in a PostgreSQL database
* perform queries on the database
  
<p align="center">
  <img src="https://github.com/RoberthGo/ReadSearch/assets/109862992/f0ad3ad0-0e1f-4e34-a064-15d390d40948">
</p>

* Search book by title - Allows you to search for books by querying the Gutendex API and store the results in the PostgreSQL database.
* List registered books - Displays a catalog of all books that have been registered in the local database.
* List registered authors - Displays a list of all authors whose works are included in the database.
* List of living authors between certain years - Allows you to view authors who are alive within a specific range of years.
* List of books by language - Displays books categorized by the language in which they are written.
* Top 10 most downloaded books in the API - Displays the 10 most popular books according to the Gutendex API download count.
* Top 10 most downloaded registered books - Similar to option 6, but specifically for books registered in the local database.
* Exit - Closes the ReadSearch application.
</br></br>



## Prerequisites 📋
To run the program, you need the following technologies used during its development:
* Java JDK: You must have Java Development Kit version 17 or higher installed. You can download it [here](https://www.oracle.com/java/technologies/downloads/#java17).
* PostgreSQL: version 14.12 or higher, You can download it [here](https://www.postgresql.org/download/)
* Maven: version 4 or higher, for dependency management and project construction.
* IntelliJ IDEA: It is the IDE used for the development of the project. Although it is not strictly necessary to run the program, its use is recommended to facilitate the development and code management. 
  development and code management. You can download IntelliJ IDEA [here](https://www.jetbrains.com/es-es/idea/).
</br></br>



## Installation 🔧
1. Clone the application
```  
git clone https://github.com/RoberthGo/ReadSearch
```
2. Create PostgreSql database
```
CREATE DATABASE read_search
```
3. Configure the file [application.properties](src/main/resources/application.properties)
<p align="center">
  <img src="https://github.com/RoberthGo/ReadSearch/assets/109862992/463767e8-106d-4c5c-8042-c5f1310f1855")>
</p>
Replace the environment variables with the corresponding data from your PostgreSQL.
Or create environment variables with the same names and assign the appropriate values. </br>
Note: If you run the above SQL command as is, ${DB_NAME} will be read_search otherwise put the name of your database.</br>
5. ready, now you can run the application with your IDE or Maven.
</br></br>




## Built with 🛠️
* Java JDK - Programming Language Used
* Spring Boot - Java Framework used
* Hibernate: Persistence provider used by JPA.
* Spring Data JPA: Used for object-relational mapping and data persistence.
* Maven - Dependency handler.
* Intellij - Integrated Development Environment (IDE) used for the project.
* PostgreSQL - Database management system used in the project.
* Jackson Databind: Convert data between JSON and Java objects 
</br></br>



## Author ✒️
* **Roberto Gordillo Herrera** - [Roberth_G](https://github.com/RoberthGo)
</br>


## Licencia 📄
This project is under the MIT License - see the [LICENSE](LICENSE) file for details.
