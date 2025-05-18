; example2.nsi
;
; This script is based on example1.nsi, but it remember the directory, 
; has uninstall support and (optionally) installs start menu shortcuts.
;
; It will install example2.nsi into a directory that the user selects,

;--------------------------------

; The name of the installer
Name "Watson's Adventure"

; Set the icon
; Icon "fenor.ico"

; The file to write
OutFile "WatsonInstall.exe"

LoadLanguageFile "${NSISDIR}\Contrib\Language files\English.nlf"
;--------------------------------
;Version Information

VIProductVersion "0.0.0.1"
VIAddVersionKey /LANG=${LANG_ENGLISH} "ProductName" "Watson's Adventure"
VIAddVersionKey /LANG=${LANG_ENGLISH} "Comments" ""
VIAddVersionKey /LANG=${LANG_ENGLISH} "CompanyName" "Dark Arai Software"
VIAddVersionKey /LANG=${LANG_ENGLISH} "LegalTrademarks" "Test Application is a trademark of Dark Arai Software"
VIAddVersionKey /LANG=${LANG_ENGLISH} "LegalCopyright" "Copyright 2014 Dark Arai Software"
VIAddVersionKey /LANG=${LANG_ENGLISH} "FileDescription" "Watson's Adventure"
VIAddVersionKey /LANG=${LANG_ENGLISH} "FileVersion" "0.0.0.1"

; The default installation directory
InstallDir "$PROGRAMFILES\DarkAraiSoftware\Watsons Adventure"

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\DarkAraiSoftware\Watsons Adventure" "Install_Dir"

; Request application privileges for Windows Vista
RequestExecutionLevel admin

;--------------------------------

; Pages

Page components
Page directory
Page instfiles

UninstPage uninstConfirm
UninstPage instfiles

;--------------------------------

; The stuff to install
Section "Watson's Adventure (required)"

  SectionIn RO
  
  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put file there
  File "Watson.jar"
  File "Watson.bat"
  CreateDirectory "$INSTDIR\data"
  CreateDirectory "$INSTDIR\data\images"
  CreateDirectory "$INSTDIR\data\maps"
  CreateDirectory "$INSTDIR\saves"
  
  SetOutPath "$INSTDIR\data\images"
  File "data\images\*.*"
  SetOutPath "$INSTDIR\data"
  File "data\*.fnt"
  File "data\*.png"
  File "data\*.dll"
  SetOutPath "$INSTDIR\data\maps"
  File "data\maps\*.*"
  SetOutPath "$INSTDIR\saves"
  File "saves\*.*"
  SetOutPath "$INSTDIR\"
  
  
  ; Write the installation path into the registry
  WriteRegStr HKLM "SOFTWARE\DarkAraiSoftware\Watsons Adventure" "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\DarkAraiSoftware\Watsons Adventure" "DisplayName" "Watsons Adventure"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\DarkAraiSoftware\Watsons Adventure" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\DarkAraiSoftware\Watsons Adventure" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\DarkAraiSoftware\Watsons Adventure" "NoRepair" 1
  WriteUninstaller "uninstall.exe"
  
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"

  CreateDirectory "$SMPROGRAMS\Dark Arai Software\Watsons Adventure"
  CreateShortCut "$SMPROGRAMS\Dark Arai Software\Watsons Adventure\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\Dark Arai Software\Watsons Adventure\Watson's Adventure.lnk" "$INSTDIR\Watson.bat" "" "$INSTDIR\Watson.bat" 0
  
SectionEnd

;--------------------------------

; Uninstaller

Section "Uninstall"
  
  ; Remove registry keys
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\DarkAraiSoftware\Watsons Adventure"
  DeleteRegKey HKLM "SOFTWARE\DarkAraiSoftware\Watsons Adventure"

  ; Remove files and uninstaller
  Delete $INSTDIR\watsoninstall.nsi
  Delete $INSTDIR\uninstall.exe

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\Dark Arai Software\Watsons Adventure\*.*"

  ; Remove directories used
  RMDir "$SMPROGRAMS\Dark Arai Software\Watsons Adventure"
  RMDir "$SMPROGRAMS\Dark Arai Software"
  RMDir "$INSTDIR"

SectionEnd
