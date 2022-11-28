FROM openjdk:19-alpine
ADD target/DiaryApp-0.0.1-SNAPSHOT.jar diaryapp.jar
EXPOSE 5555
ENTRYPOINT ["java","-jar","/diaryapp.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]