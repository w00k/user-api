# user-api
Corresponde a una api para el manejo y mantencion de usuarios, 

## Acerca de user-api
Consta de una app desarrolalda en Spring Boot,  con H2 y con Hikari (para manerar el pool de conexiones, si se quiere probar en una base de datos).

## Diagrama de la app

### Base de datos
En base de datos, se generan desde H2, estas tablas se relacionan entre si para almacenar la entidad **user** y **phone**, mantienen una relacion unidireccional.  

Tabla **user_db**, corresponde a la entidad **user**.

| id | is_active | create_date  | email | last_login | name | password | token | update_date |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| Id y secuancia auto generada en H2  | Tipo boolean, para determinar si el usuario esta activo o no  | Fecha de creacion | email, debe ser unico | Registra el ultimo login | Nombre de la persona | Password, debe contener una letra Mayuscula y al menos 2 numeros | Token auto generado en el Java | fecha de actualizacion |

Tabla **phone_db**, corresponde a la entidad **phone** que corresponde a uno a muchos, esd decir un **user** puede poseer muchos **phone**.

| id | city_code | country_code  | number | user_id |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| Id y secuencia auto generada en H2| Codigo de la ciudad | Numero de telefono | Clave foranera, id de la tabla **user_db** |

### Java
En Java, al levantar el proyecto se expone el swagger desde [http://localhost:8084/swagger-ui.html ](http://localhost:8084/swagger-ui.html), la app maneja un intercertor para el manejo de errores y exposicion de mensajes. 

**Nota**: El body de la apis corresponde a un solo tipo y que espera diferentes variables para las diferentes apis.

#### Delete user

Elimina un usuario existente.

| Method | URI | Parametros | Observacion |
| ------------- | ------------- | ------------- | ------------- |
| DELETE | /api/v1/db/delete/ | id | Id de tipo numerico y que viaja como parametro en la URI |

Ejemplo: 

Request: http://localhost:8084/api/v1/db/delete/1

Response: 
```json
{
    "id": "1",
    "name": "Lala",
    "email": "wooksdggf@gmail.com",
    "password": "A12ffsd5",
    "phones": [
        {
            "number": "56911111111",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56922222222",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56933333333",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        }
    ],
    "createDate": "2021-09-27T05:33:47.407+00:00",
    "updateDate": "2021-09-27T05:33:47.407+00:00",
    "lastLogin": "2021-09-27T05:33:47.407+00:00",
    "token": "55da0308-45d0-435b-990c-ac7c0774dedb",
    "active": true
}
```

#### Get user

Retorna un usuario existente con todos sus datos.

| Method | URI | Parametros | Observacion |
| ------------- | ------------- | ------------- | ------------- |
| GET | /api/v1/db/get/ | id | Id de tipo numerico y que viaja como parametro en la URI |

Ejemplo: 

Request: http://localhost:8084/api/v1/db/get/1

Response: 
```json
{
    "id": "1",
    "name": "Lala",
    "email": "wooksdggf@gmail.com",
    "password": "A12ffsd5",
    "phones": [
        {
            "number": "56911111111",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56922222222",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56933333333",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        }
    ],
    "createDate": "2021-09-27T05:33:47.407+00:00",
    "updateDate": "2021-09-27T05:33:47.407+00:00",
    "lastLogin": "2021-09-27T05:33:47.407+00:00",
    "token": "55da0308-45d0-435b-990c-ac7c0774dedb",
    "active": true
}
```

#### POST Login

| Method | URI | Parametros | Observacion |
| ------------- | ------------- | ------------- | ------------- |
| POST | /api/v1/db/login | Body a continuacion | Raw de tipo rest/json, que se envia en el post |

Ejemplo: 

Request con los campos obligatorio: 
```json
{
    "id": "5",
    "password": "A12ffsd5",
    "token": "e940b6e5-853e-487d-9c7d-613aace4be90"
}
```

Response: 
```json
{
    "id": 5,
    "createDate": "2021-09-27T05:38:19.839+00:00",
    "updateDate": "2021-09-27T05:38:19.839+00:00",
    "lastLogin": "2021-09-27T05:40:31.674+00:00",
    "token": "e940b6e5-853e-487d-9c7d-613aace4be90",
    "active": true
}
```

#### PUT user

| Method | URI | Parametros | Observacion |
| ------------- | ------------- | ------------- | ------------- |
| PUT | /api/v1/db/put | Body a continuacion | Raw de tipo rest/json, que se envia en el put |

Ejemplo: 

Request: 
```json
{
    "name": "Lala",
    "email": "wooksdggf@gmail.com",
    "password": "A12ffsd5",
    "active": true,
    "phones": [
        {
            "number": "56911111111",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56922222222",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56933333333",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        }
    ]
}
```

Response: 
```json
{
    "id": 5,
    "createDate": "2021-09-27T05:38:19.839+00:00",
    "updateDate": "2021-09-27T05:38:19.839+00:00",
    "lastLogin": "2021-09-27T05:38:19.839+00:00",
    "token": "e940b6e5-853e-487d-9c7d-613aace4be90",
    "active": true
}
```

#### POST update

| Method | URI | Parametros | Observacion |
| ------------- | ------------- | ------------- | ------------- |
| POST | /api/v1/db/update | Body a continuacion | Raw de tipo rest/json, que se envia en el post |

Ejemplo: 

Request: 
```json
{
    "id": "1",
    "name": "Lala",
    "email": "wook82@gmail.com",
    "password": "A12ffsd5",
    "token": "8d937060-6d13-4f59-ada1-bf89e90e6344", //el valor del token es obligatorio 
    "active": false,
    "phones": [
        {
            "number": "56911111111",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56922222222",
            "cityCode": "Santiago",
            "countryCode": "Chile"
        },
        {
            "number": "56933333333",
            "cityCode": "Villarica",
            "countryCode": "Chile"
        }
    ]
}
```

Response:
```json
{
    "id": 1,
    "createDate": "2021-09-27T05:48:49.833+00:00",
    "updateDate": "2021-09-27T05:49:09.595+00:00",
    "lastLogin": "2021-09-27T05:48:49.833+00:00",
    "token": "8d937060-6d13-4f59-ada1-bf89e90e6344",
    "active": false
}
```