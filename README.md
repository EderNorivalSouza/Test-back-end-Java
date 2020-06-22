Test-back-end-java - Poi-Atomic

RESUME
Project to list point of interest(poi) sending a request with reference poi and max distance, adding a new poi is possible to.

PRE REQUISITE
Java - 11
Maven - 3.6.3
SpringBoot - 2.2.6
H2
Postgresql - 42.2.12
Junit - 5
Windows 10

HOW TO USE
This microservice work with RESTfull requisition:
--List all poi's - GET requisition
http://localhost:8080/pois
No needed data, list all pois exists on data base.

--Find poi per Id - GET requisition
http://localhost:8080/pois/id?id={value}
value = id that you need return

--List poi's with filter - POST requisition
http://localhost:8080/pois/filter
Need send reference poi and max distance on JSON format:
{
"coordX":"{value bigger than one}",
"coordY":"{value bigger than one}",
"dmax":"{value bigger than zero}"
}

--Adding poi - POST requisition
http://localhost:8080/pois
Need send a form with:
{
"name":{String}
"coordX":"{value bigger tha zero}",
"coordY":"{value bigger tha zero}"
}

