#!/usr/bin/env sh
# Inicializa uma distribuição Maven local, sem exigir instalação global.
set -eu
BASE=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
VERSION=3.9.11
DIST="$BASE/.mvn/dist/apache-maven-$VERSION"
if [ ! -x "$DIST/bin/mvn" ]; then
  mkdir -p "$BASE/.mvn/dist"
  URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$VERSION/apache-maven-$VERSION-bin.tar.gz"
  ARCHIVE="$BASE/.mvn/dist/apache-maven-$VERSION-bin.tar.gz"
  CHECKSUM="$ARCHIVE.sha512"
  if command -v curl >/dev/null 2>&1; then
    curl --fail --location --silent --show-error "$URL" -o "$ARCHIVE"
    curl --fail --location --silent --show-error "$URL.sha512" -o "$CHECKSUM"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$URL" -O "$ARCHIVE"
    wget -q "$URL.sha512" -O "$CHECKSUM"
  else
    echo 'Instale curl ou wget para baixar o Maven.' >&2
    exit 1
  fi
  EXPECTED=$(tr -cd '0-9a-fA-F' < "$CHECKSUM" | cut -c 1-128)
  if command -v sha512sum >/dev/null 2>&1; then
    ACTUAL=$(sha512sum "$ARCHIVE" | cut -d ' ' -f 1)
  elif command -v shasum >/dev/null 2>&1; then
    ACTUAL=$(shasum -a 512 "$ARCHIVE" | cut -d ' ' -f 1)
  else
    echo 'Utilitário SHA-512 indisponível.' >&2
    exit 1
  fi
  if [ "$EXPECTED" != "$ACTUAL" ]; then
    echo 'Checksum SHA-512 do Maven divergente.' >&2
    rm -f "$ARCHIVE" "$CHECKSUM"
    exit 1
  fi
  tar -xzf "$ARCHIVE" -C "$BASE/.mvn/dist"
  rm -f "$ARCHIVE" "$CHECKSUM"
fi
exec "$DIST/bin/mvn" "$@"
