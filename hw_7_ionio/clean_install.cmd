call cd .\..
call mvn clean install
call cd .\hw_7_ionio
call mvn clean install -Plife -DskipTests
