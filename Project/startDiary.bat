@echo off
set "PROJECT_DIR=C:\Users\Тимур\IdeaProjects\ExcelJSON"
cd /d "%PROJECT_DIR%"

mvn compile exec:java

pause