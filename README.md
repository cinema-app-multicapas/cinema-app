# 🐳 Proyecto con Docker Compose
Este proyecto utiliza **Docker Compose** para facilitar el despliegue y la ejecución de todos los servicios necesarios.

- Oscar Alejandro Rodriguez Gómez
- Andres Felipe Ruge Passito
- Juan Felipe Gonzalez Quintero

## 🚀 Requisitos
Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas en tu sistema:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## 📦 Instrucciones para ejecutar el proyecto
Sigue estos pasos para clonar el repositorio y levantar los servicios con Docker Compose:
1. Clona este repositorio:
   ```bash
   git clone https://github.com/cinema-app-multicapas/cinema-app.git
   cd cinema-app
   ```

2. Ejecuta Docker Compose para construir e iniciar todos los servicios:
   ```bash
   docker-compose up -d
   ```

3. Una vez que todos los contenedores estén en funcionamiento, se puede acceder a la aplicación en:
   ```
   http://localhost:4200
   ```

## 🧩 Estructura del proyecto
El proyecto está compuesto por los siguientes servicios:
- **frontend**: Aplicación Angular para gestionar directores de cine
- **backend**: API REST desarrollada en SpringBoot
- **database**: Servicio PostgreSQL para almacenamiento de datos

## 🧹 Detener y limpiar
Para detener todos los servicios:
```bash
docker-compose down
```

Para detener y eliminar todos los volúmenes (esto eliminará todos los datos):
```bash
docker-compose down -v
```
