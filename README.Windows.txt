Install and run info

1. Install JOGL libs into maven repo:
go to etc/common_libs folder and run mvn_install_common_libs.bat

2. Compile project (mvn clean install -Dmaven.test.skip)

3. Run runGame.bat in space-invaders-gl folder.

NOTES
If you are running application under Windows x86, you need to replace dlls in "etc\gl_native_libs" by dlls from "etc\gl_native_libs\win_x86"  
