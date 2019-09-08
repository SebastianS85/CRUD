call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runexplorer
echo.
echo runcrud has ERROR
goto fail

:runexplorer

 start "" http://localhost:8080/crud/v1/task/getTasks
:end
echo.
echo Work is finished.