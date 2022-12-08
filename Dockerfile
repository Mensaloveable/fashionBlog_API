FROM openjdk:17
EXPOSE 8080
ADD ./target/fashionBlog_API.jar fashionBlog_API.jar
ENTRYPOINT ["java","jar","/fashion-blog-api.jar"]