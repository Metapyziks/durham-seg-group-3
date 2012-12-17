TestServer.exe: 
	gmcs *.cs Entities/*.cs Properties/*.cs Requests/*.cs Responses/*.cs \
		-r:System.Data.dll,Mono.Data.Sqlite.dll,Nini.dll \
		-d:LINUX \
		-main:TestServer.Program \
		-out:TestServer.exe

debug:
	gmcs *.cs Entities/*.cs Properties/*.cs Requests/*.cs Responses/*.cs \
		-r:System.Data.dll,Mono.Data.Sqlite.dll,Nini.dll \
		-d:LINUX,DEBUG \
		-main:TestServer.Program \
		-out:TestServer.exe

clean:
	rm TestServer.exe Database.db
