$ErrorActionPreference = 'Stop'
$base = Split-Path -Parent $PSScriptRoot
$version = '3.9.11'
$dist = Join-Path $base ".mvn/dist/apache-maven-$version"
$mvn = Join-Path $dist 'bin/mvn.cmd'
if (-not (Test-Path $mvn)) {
    $cache = Join-Path $base '.mvn/dist'
    New-Item -Path $cache -ItemType Directory -Force | Out-Null
    $url = "https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$version/apache-maven-$version-bin.zip"
    $arquivo = Join-Path $cache "apache-maven-$version-bin.zip"
    Invoke-WebRequest -UseBasicParsing -Uri $url -OutFile $arquivo
    $checksum = (Invoke-WebRequest -UseBasicParsing -Uri "$url.sha512").Content.Trim()
    $real = (Get-FileHash -Algorithm SHA512 -Path $arquivo).Hash
    if ($real -ine $checksum.Substring(0, 128)) {
        Remove-Item $arquivo -Force
        throw 'Checksum SHA-512 do Maven divergente.'
    }
    Expand-Archive -Path $arquivo -DestinationPath $cache -Force
    Remove-Item $arquivo -Force
}
& $mvn @args
exit $LASTEXITCODE
