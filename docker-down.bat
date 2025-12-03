@echo off
docker compose -f infra/docker/docker-compose.yml down
echo.
echo Server stopped.
pause
