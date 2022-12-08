FROM openjdk:17
EXPOSE 8080
ADD target/fashionBlogAPI-0.0.1-SNAPSHOT.jar fashion-blog-api.jar
ENTRYPOINT ["java","jar","/fashion-blog-api.jar"]