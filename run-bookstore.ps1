param(
  [string]$MainClass = "ui.Main"
)

$ProjectDir = $PSScriptRoot
Set-Location $ProjectDir

$src = ".\src\main"
$out = ".\out"

if (-not (Test-Path $out)) {
  New-Item -ItemType Directory -Force $out | Out-Null
}

# Automatically add all existing JAR files in the project to the classpath (without moving or adding any files).
$jarFiles = Get-ChildItem -Recurse -Filter *.jar
$cp = $out
if ($jarFiles) { $cp = $cp + ";" + ($jarFiles.FullName -join ";") }

javac -cp $cp -d $out (Get-ChildItem -Recurse -Filter *.java $src).FullName
if ($LASTEXITCODE -ne 0) { Write-Host "Compile failed"; exit 1 }

java -cp $cp $MainClass
