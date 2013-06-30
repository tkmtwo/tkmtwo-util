
@ECHO OFF
java ^
  -Dlogback.configurationFile=logback-console.xml  -DLOG_LEVEL=INFO ^
  -jar target\time-directory-archive.jar ^
  C:\tmp\DateTimeDirectoryArchiver\test ^
  C:\tmp\dtdaTmp ^
  P1D P2D
