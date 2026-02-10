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

# 自动把项目里已有的所有 jar 加进 classpath（不移动、不新增任何文件）
$jarFiles = Get-ChildItem -Recurse -Filter *.jar
$cp = $out
if ($jarFiles) { $cp = $cp + ";" + ($jarFiles.FullName -join ";") }

javac -cp $cp -d $out (Get-ChildItem -Recurse -Filter *.java $src).FullName
if ($LASTEXITCODE -ne 0) { Write-Host "Compile failed"; exit 1 }

java -cp $cp $MainClass
