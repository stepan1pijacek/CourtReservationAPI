# CourtReservationWebService



Demonstration of a simple RESTful web service using SPRING and JAVA.
This project has been created as interview assignment from InQool. 

This web service provides management services over tennis courts and their reservations, with basic CRUD operations.

## Basic setup
In order to run this web service you need to have MySQL running on localhost with created database. Creation script, is included in this project, in the folder `DB`. 
After that, is done you may run the web service, it will be on address: `http://localhost:8080`.

Remember that end points only consume `application/json` and output `application/json`. If you try to send anything else, you will get `415 UNSUPPORTED MEDIA TYPE`.

## REST Endpoints

Note that in order to use REST endpoint you need to get JWT token first! Requests must have JWT in a form of Bearer token!

### Authentication JSON example
In current state, users credentials, are hard coded into the authentication, but with a few changes to database and code you can create functional login system.
Only Endpoint that is accessible without JWT is authentication. Without token, you will receive `401 UNAUTHORISED`.
```json
{
  "username":"javainuse",
  "password":"password"
}
```

### CREATE reservation example

```json
{
    "CourtsID" : 1,
    "TimeInterval" : 40,
    "GameType" : "false",
    "PhoneNumber" : 123456789,
    "Surname" : "*****"
}
```

### CREATE Court

```json
{
    "Surface": "Grass",
    "PricePerMin": 20
}
```
### DELETE/GET reservation by phone

```json
{
  "PhoneNumber": 123456789
}
```

### DELETE/GET reservations by CourtID

```json
{
  "CourtsID": 1
}
```

### DELETE court

```json
{
  "ID": 1
}
```

The following REST endpoints are available upon deployment of the reservation management system:

| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `POST` | `http://localhost:8080/authenticate` | Upon receiving JSON with username and password it returns JWT token | <ul><li>`200 OK`</li></ul> |
| `POST` | `http://localhost:8080/create/createReservation` | Upon receiving JSON with reservation information, reservation is created and returns price | <ul><li>`201 CREATED` if reservation creation is success</li><li>`406 NOT ACCEPTABLE` if time interval is incorrect or phone number is invalid</li></ul> |
| `POST` | `http://localhost:8080/create/createCourt` | Creates a new court based on the payload contained in the request body | <ul><li>`201 CREATED` if order successfully created</li></ul> |
| `DELETE` | `http://localhost:8080/delete/deleteByPhone` | Deletes an existing reservation that corresponds to the supplied phone number | <ul><li>`202 ACCEPTED` if reservation successfully deleted</li><li> `404 NOT FOUND` if phone number does not exist</li></ul> |
| `DELETE` | `http://localhost:8080/delete/deleteByCourt` | Deletes an existing reservation that corresponds to the supplied court ID | <ul><li>`202 ACCEPTED` if reservation successfully deleted</li><li>`404 Not Found` if court does not exist</li></ul> |
| `DELETE` | `http://localhost:8080/delete/deleteCourt` | Deletes an existing court that corresponds to the supplied court ID | <ul><li>`202 ACCEPTED` if court successfully deleted</li><li>`404 Not Found` if court does not exist</li></ul> |
| `GET` | `http://localhost:8080/read/readCourtList` | Obtains a list of existing courts | <ul><li>`200 OK` </li></ul> |
| `GET` | `http://localhost:8080/read/readReservationWPhoneNumber` | Obtains a list of existing reservations corresponding to provided phone number |  <ul><li>`200 OK`</li><li> `404 NOT FOUND` if phone number does not exist</li></ul> |
| `GET` | `http://localhost:8080/read/readReservationPerCourt` | Obtains a list of existing reservations corresponding to provided court ID |  <ul><li>`200 OK`</li><li> `404 NOT FOUND` if court ID does not exist</li></ul> |
