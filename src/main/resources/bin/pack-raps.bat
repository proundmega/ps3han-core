@echo off

set PYTHON=c:\Python27
set PATH=%PYTHON%;%PATH%
set PKG_NAME=%2%.pkg

pkg_exdata.exe --contentid %1 %2 %PKG_NAME% C:\Users\sakshi\Documents\NetBeansProjects\ps3han-core\target\output\riff