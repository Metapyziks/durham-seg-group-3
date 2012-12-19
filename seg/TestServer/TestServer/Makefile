CSC = mcs

CSVERSION = future

SRC = -recurse:'*.cs'

DEF = LINUX

SYSLIBS = System.Data.dll,System.Core.dll
NINI = Nini.dll
SQLLIB = Mono.Data.Sqlite.dll

MAINCLASS = TestServer.Program

TARGET = TestServer.exe

TestServer.exe: 
	$(CSC) -langversion:$(CSVERSION) $(SRC) -r:$(SYSLIBS),$(NINI),$(SQLLIB) -d:$(DEF) \
		-main:$(MAINCLASS) -out:$(TARGET)

debug:
	$(CSC) -langversion:$(CSVERSION) $(SRC) -r:$(SYSLIBS),$(NINI),$(SQLLIB) -d:$(DEF),DEBUG \
		-main:$(MAINCLASS) -out:$(TARGET)

clean:
	rm TestServer.exe Database.db
