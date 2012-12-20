CSC = mcs

CSVERSION = future

SRC = -recurse:'*.cs'

DEF = LINUX

RESDIR = res
BINDIR = bin
LIBDIR = extlibs

DEBUGBINDIR = $(BINDIR)/Debug
RELEASEBINDIR = $(BINDIR)/Release

SYSLIBS = System.Data.dll,System.Core.dll,System.Web.dll
NINI = Nini.dll
SQLLIB = Mono.Data.Sqlite.dll

MAINCLASS = TestServer.Program

TARGET = TestServer.exe

release: 
	$(CSC) -langversion:$(CSVERSION) $(SRC) -r:$(SYSLIBS),$(LIBDIR)/$(NINI),$(SQLLIB) -d:$(DEF) \
		-main:$(MAINCLASS) -out:$(DEBUGBINDIR)/$(TARGET)
	cp -r $(RESDIR) $(DEBUGBINDIR)/$(RESDIR)
	cp config.ini $(DEBUGBINDIR)
	cp $(NINI) $(DEBUGBINDIR)

debug:
	$(CSC) -langversion:$(CSVERSION) $(SRC) -r:$(SYSLIBS),$(LIBDIR)/$(NINI),$(SQLLIB) -d:$(DEF),DEBUG \
		-main:$(MAINCLASS) -out:$(DEBUGBINDIR)/$(TARGET)
	cp -r $(RESDIR) $(DEBUGBINDIR)/$(RESDIR)
	cp config.ini $(DEBUGBINDIR)
	cp $(NINI) $(DEBUGBINDIR)

clean:
	rm TestServer.exe
