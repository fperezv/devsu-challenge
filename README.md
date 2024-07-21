# DEVSU Challenge - Fabian Perez

Herramientas usadas:

- Java 17

- Spring Boot 3

- MySQL

- Maven 3

- JUnit 5

- Mockito

- Test Containers

Características

- Arquitectura hexagonal

## Clonar repositorio

```shell
git clone https://github.com/fperezv/devsu-challenge.git
```

## Despliegue

```sheell
docker compose up -d
```

Se desplegarán 3 contenedores: 

- base de datos -> **devsu-database**

- microservicio usuario -> **devsu-user**

- microservicio cuenta -> **devsu-account**

#### Credenciales para acceder a la base de datos

- Host -> localhost

- Puerto -> 3306

- Usuario -> root

- Contraseña -> devsu-database

## Consultar por Postman



> No es necesario modificar ningún dato de la colección para probar, a menos que así se quiera. Ya que todo está configurado para funcionar para un caso de uso en específico.
> 
> Para esto se está usando las opciones "Pre-request Script" y "Tests" que brinda Postman para poder realizar diferentes pruebas.



1. Importar colección en Postman (**Devsu Challenge.postman_collection.json**), el archivo se encuentra ubicado en la ruta inicial del proyecto. Ref: [Import data into Postman | Postman Learning Center](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/)

2. Crear un entorno de Postman vacío. Ref: [Group sets of variables in Postman using environments | Postman Learning Center](https://learning.postman.com/docs/sending-requests/variables/managing-environments/#create-an-environment)

3. Ejecutar las pruebas en el orden que han sido importadas 1 por 1.


