@echo off
docker compose -f infra/docker/docker-compose.yml up -d --build
echo.
echo Server started at http://localhost:8080/ode
pause
