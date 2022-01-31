@echo off
call mvn clean install
call java -jar target/unit_final_project-0.0.1-SNAPSHOT.jar