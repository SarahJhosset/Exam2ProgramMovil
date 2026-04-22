#!/bin/bash

# Configuración
# REEMPLAZA ESTO CON TU EXPORT KEY DE LOCO
API_KEY="dWJems4SlBLN8RsJCI1pFVf45knkN8Ex"

# CAMBIO: Apuntar a commonMain/composeResources para que funcione en todas las plataformas
RES_PATH="composeApp/src/commonMain/composeResources"

download() {
    local loco_code=$1
    local folder_suffix=$2

    echo "Iniciando descarga: $loco_code..."

    # Asegurar que la carpeta existe
    mkdir -p "$RES_PATH/$folder_suffix"

    # Descarga directa desde la API de Loco en formato Android XML
    curl -u "$API_KEY:" "https://localise.biz/api/export/locale/$loco_code.xml?format=android" > "$RES_PATH/$folder_suffix/strings.xml"

    if [ $? -eq 0 ]; then
        echo "✅ Sincronizado: $folder_suffix/strings.xml"
    else
        echo "❌ Error al descargar idioma: $loco_code"
    fi
}

# CONFIGURACIÓN: Español como base e Inglés como traducción
# 1. Español va a "values" (idioma por defecto)
download "en" "values"

# 2. Inglés va a "values-en" (específico para inglés)
download "es" "values-es"

echo "--- Proceso de traducción finalizado ---"