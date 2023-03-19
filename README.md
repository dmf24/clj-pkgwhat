## clj-pkgwhat

This is a trivial babashka script to resolve common package manager queries from standard names.

This currently supports distros like Ubuntu and Debian, Red Hat and Fedora, Alpine and Arch linux.

### Install

This will install a script along with symlinks to $HOME/bin

```
git clone https://github.com/dmf24/clj-pkgwhat.git
cd clj-pkgwhat
./install.sh
```

To install to /usr/local/bin

```
sudo ./install.sh /usr/local/bin
```

### files-in-package

This lists all files in the specified package.

```
Singularity> files-in-package emacs-nox | tail
usr/share/icons/hicolor/48x48/apps/emacs.png
usr/share/icons/hicolor/scalable/apps/emacs.ico
usr/share/icons/hicolor/scalable/apps/emacs.svg
usr/share/icons/hicolor/scalable/mimetypes/emacs-document.svg
usr/share/icons/hicolor/scalable/mimetypes/emacs-document23.svg
usr/share/metainfo/emacs.metainfo.xml
var/games/emacs/snake-scores
var/games/emacs/tetris-scores
```

### installed-packages

This lists all installed packages known by the package manager.

```
$ singularity-3.11.0 shell alpine.sif 
Singularity> installed-packages | tail -4
restic-bash-completion-0.14.0-r5 x86_64 {restic} (BSD-2-Clause)
fbida-2.14-r2 x86_64 {fbida} (GPL-2.0-only)
font-iosevka-curly-slab-16.8.4-r0 x86_64 {font-iosevka} (OFL-1.1)
```

### package-info

This shows detailed information about the package.

```
Singularity> package-info emacs
Loaded plugins: fastestmirror, ovl
Loading mirror speeds from cached hostfile
 * base: coresite.mm.fcix.net
 * extras: centos.mirror.constant.com
 * updates: centos.mirror.constant.com
Installed Packages
Name        : emacs
Arch        : x86_64
Epoch       : 1
Version     : 24.3
Release     : 23.el7
Size        : 14 M
Repo        : installed
From repo   : base
Summary     : GNU Emacs text editor
URL         : http://www.gnu.org/software/emacs/
License     : GPLv3+
Description : Emacs is a powerful, customizable, self-documenting, modeless text
            : editor. Emacs contains special code editing features, a scripting
            : language (elisp), and the capability to read mail, news, and more
            : without leaving the editor.
            : 
            : This package provides an emacs binary with support for X windows.
```

### which-package-has-file

This accepts a path argument and finds which package owns the given file.

```
$ singularity-3.11.0 shell alpine.sif 
Singularity> export PATH=$PATH:$HOME/bin
Singularity> which-package-has-file /usr/bin/emacs
/usr/bin/emacs is owned by emacs-nox-28.2-r3
```

```
$ singularity-3.11.0 shell arch-base.sif
Singularity> which-package-has-file /bin/bash
/usr/bin/bash is owned by bash 5.1.004-1
```

```
$ singularity-3.11.0 shell centos-7-base.sif
Singularity> which-package-has-file /bin/bash
bash-0:4.2.46-34.el7.x86_64
```
